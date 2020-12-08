package org.jaybaws.adventofcode.y2019.day9;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class IntCodeComputer implements Runnable, Callable<BigInteger> {

    private boolean TRACE = false;

    private Map<BigInteger, BigInteger> memory = new HashMap<BigInteger, BigInteger>();
    private BigInteger instructionPointer = BigInteger.ZERO;
    private BigInteger relativeBase = BigInteger.ZERO;

    private BlockingQueue<BigInteger> inputvalue;
    private BlockingQueue<BigInteger> outputvalue;

    private BigInteger lastOutputValue = BigInteger.valueOf(-1);

    protected void setInput(BlockingQueue<BigInteger> input) {
        this.inputvalue = input;
    }

    protected void setOutput(BlockingQueue<BigInteger> output) {
        this.outputvalue = output;
    }

    public IntCodeComputer(List<BigInteger> intcodes, BlockingQueue<BigInteger> input) {
        BigInteger i = BigInteger.ZERO;
        for (BigInteger code : intcodes) {
            memory.put(i, code);
            i = i.add(BigInteger.ONE);
        }

        if (input != null)
            setInput(input);

        outputvalue = new LinkedBlockingQueue<BigInteger>();
    }

    public IntCodeComputer(List<BigInteger> intcodes) {
        this(intcodes, null);
    }

    public void process() {
        controlloop:
        while (true) {
            String opCodeStr = padLeftZeros(read(instructionPointer).toString(), 5);

            String de = opCodeStr.substring(3);
            char c = opCodeStr.substring(2, 3).charAt(0);
            char b = opCodeStr.substring(1, 2).charAt(0);
            char a = opCodeStr.substring(0, 1).charAt(0);

            // System.out.println(String.format("[%s] -> de(%s) c(%s) b(%s) a(%s).", opCodeStr, de, c, b, b, a));

            switch (de) {
                case "01":
                    handle_add(getValueByOffset(1, c), getValueByOffset(2, b), getValueByOffset(3, '1')); // '1'
                    break;
                case "02":
                    handle_multiply(getValueByOffset(1, c), getValueByOffset(2, b), getValueByOffset(3, '1')); // '1'
                    break;
                case "03":
                    handle_readInt(getValueByOffset(1, '1')); // '1'
                    break;
                case "04":
                    handle_outputInt(getValueByOffset(1, c)); // '1'
                    break;
                case "05":
                    handle_jumpIfTrue(getValueByOffset(1, c), getValueByOffset(2, b));
                    break;
                case "06":
                    handle_jumpIfFalse(getValueByOffset(1, c), getValueByOffset(2, b));
                    break;
                case "07":
                    handle_LessThen(getValueByOffset(1, c), getValueByOffset(2, b), getValueByOffset(3, '1')); // '1'
                    break;
                case "08":
                    handle_Equals(getValueByOffset(1, c), getValueByOffset(2, b), getValueByOffset(3, '1')); // '1'
                    break;
                case "09":
                    handle_AdjustRelativeBase(getValueByOffset(1, '1')); // c
                    break;
                case "99":
                    break controlloop;
                default:
                    throw new RuntimeException(String.format("Invalid op-code '%s' encountered!", opCodeStr));
            }
        }
    }

    private void step(int steps) {
        instructionPointer = instructionPointer.add(BigInteger.valueOf(steps));
    }

    private void write(BigInteger pos, BigInteger val) {
        if (memory.containsKey(pos)) {
            memory.replace(pos, val);
        } else {
            memory.put(pos, val);
        }
    }

    private BigInteger read(BigInteger pos) {
        if (pos.compareTo(BigInteger.ZERO) == -1)
            throw new IllegalArgumentException(String.format("Negative position %d not allowed.", pos));

        if (memory.containsKey(pos)) {
            return memory.get(pos);
        } else {
            return BigInteger.ZERO;
        }
    }

    private void handle_add(BigInteger val1, BigInteger val2, BigInteger resultPos) {
        if (TRACE)
            System.out.println(String.format("[ADD] %d + %d, store @ %d", val1, val2, resultPos));
        write(resultPos, val1.add(val2));

        step(4); // advance 4 steps.
    }

    private void handle_multiply(BigInteger val1, BigInteger val2, BigInteger resultPos) {
        if (TRACE)
            System.out.println(String.format("[MULTIPLY] %d * %d, store @ %d", val1, val2, resultPos));
        write(resultPos, val1.multiply(val2));

        step(4); // advance 4 steps.
    }

    private void handle_readInt(BigInteger resultPos) {
        try {
            if (TRACE)
                System.out.println(String.format("[INPUT] Providing value '%d', and storing it at %d", inputvalue.peek(), resultPos));
            write(resultPos, inputvalue.poll(10, TimeUnit.SECONDS));

            step(2); // advance 2 steps.
        } catch (NullPointerException e) {
            throw new RuntimeException("Unable to read from input-queue. Is it null, or depleted?", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Aborted while reading/waiting on input", e);
        }
    }

    private void handle_outputInt(BigInteger val) {
        if (TRACE)
            System.out.println(String.format("--> handle_outputInt(%s)", val));

        lastOutputValue = val;
        outputvalue.offer(val);
        if (TRACE)
            System.out.println(String.format("[OUTPUT] value '%s'.", lastOutputValue));
        step(2); // advance 2 steps.
    }

    private void handle_jumpIfTrue(BigInteger param1, BigInteger param2) {
        if (TRACE)
            System.out.println(String.format("[jump-if-true] if %d != 0, go-to %d.", param1, param2));
        if (!param1.equals(BigInteger.ZERO)) {
            instructionPointer = param2;
        } else {
            step(3); // Do nothing else, but advance 3 steps.
        }
    }

    private void handle_jumpIfFalse(BigInteger param1, BigInteger param2) {
        if (TRACE)
            System.out.println(String.format("[jump-if-false] if %d == 0, go-to %d.", param1, param2));
        if (param1.equals(BigInteger.ZERO)) {
            instructionPointer = param2;
        } else {
            step(3); // Do nothing else, but advance 3 steps.
        }
    }

    private void handle_LessThen(BigInteger val1, BigInteger val2, BigInteger resultPos) {
        if (TRACE)
            System.out.println(String.format("[less-then] if %d < %d, write '1' at %d, else write '0'.", val1, val2, resultPos));

        if (val1.compareTo(val2) == -1) {
            write(resultPos, BigInteger.ONE);
        } else {
            write(resultPos, BigInteger.ZERO);
        }
        step(4); // advance 4 steps.
    }

    private void handle_Equals(BigInteger val1, BigInteger val2, BigInteger resultPos) {
        if (TRACE)
            System.out.println(String.format("[equals] if %d == %d, write '1' at %d, else write '0'.", val1, val2, resultPos));

        if (val1 == val2) {
            write(resultPos, BigInteger.ONE);
        } else {
            write(resultPos, BigInteger.ZERO);
        }
        step(4); // advance 4 steps.
    }

    private void handle_AdjustRelativeBase(BigInteger val1) {
        if (TRACE)
            System.out.println(String.format("--> handle_AdjustRelativeBase(%s) (old: %s)", val1, relativeBase));

        relativeBase = val1;

        step(2); // advance 2 steps.
    }

    public BigInteger getLastOutputValue() {
        return lastOutputValue;
    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    private BigInteger getValueByOffset(int offset, char paramMode) {
        BigInteger value = read(instructionPointer.add(BigInteger.valueOf(offset)));

        switch (paramMode) {
            case '0': // Position mode
                return read(value);
            case '1': // Immediate mode
                return value;
            case '2': // Relative mode
                return read(relativeBase.add(value)); // memory.get(relativeBase.add(value).add(BigInteger.valueOf(offset)));
            default:
                throw new IllegalArgumentException(String.format("Mode '%c' is not a valid parameter mode!", paramMode));
        }
    }

    public void enableTracing() {
        this.TRACE = true;
    }

    public BlockingQueue<BigInteger> getOutput() {
        return this.outputvalue;
    }

    public BlockingQueue<BigInteger> getInput() {
        return this.inputvalue;
    }

    public BigInteger compute() {
        process();
        return getLastOutputValue();
    }

    @Override
    public BigInteger call() throws Exception {
        process();
        return getLastOutputValue();
    }

    @Override
    public void run() {
        System.out.println("START RUN!");
        process();
        System.out.println("END RUN!");
    }

    public static IntCodeComputer fromString(String s, BlockingQueue<BigInteger> input, BlockingQueue<BigInteger> output) {
        List<BigInteger> program = new ArrayList<BigInteger>();
        String[] ints = s.split(",");
        for (String i : ints) {
            program.add(new BigInteger(i));
        }

        IntCodeComputer c = new IntCodeComputer(program, input);
        if (output != null)
            c.setOutput(output);

        return c;
    }

    public static IntCodeComputer fromString(String s, String inputStr, BlockingQueue<BigInteger> output) {
        if (inputStr != null) {
            BlockingQueue<BigInteger> input = new LinkedBlockingQueue<BigInteger>();
            String[] ints = inputStr.split(",");
            for (String i : ints) {
                input.add(new BigInteger(i));
            }

            return IntCodeComputer.fromString(s, input, output);
        } else
            return IntCodeComputer.fromString(s, (BlockingQueue<BigInteger>) null, output);
    }
}