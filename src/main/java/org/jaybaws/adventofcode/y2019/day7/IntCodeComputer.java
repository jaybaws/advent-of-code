package org.jaybaws.adventofcode.y2019.day7;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class IntCodeComputer implements Runnable, Callable<Integer> {

    private boolean TRACE = false;

    private int[] initial_memory;
    private int[] memory;
    private int instructionPointer = 0;

    private BlockingQueue<Integer> inputvalue;
    private BlockingQueue<Integer> outputvalue;

    private int lastOutputValue = -1;

    protected void setInput(BlockingQueue<Integer> input) {
        this.inputvalue = input;
    }

    protected void setOutput(BlockingQueue<Integer> output) {
        this.outputvalue = output;
    }

    protected void setMemory(int[] intcodes) {
        this.initial_memory = intcodes;
        this.memory = intcodes;
    }

    private final void init(int[] intcodes, BlockingQueue<Integer> input) {
        setMemory(intcodes);
        setInput(input);
        this.outputvalue = new LinkedBlockingQueue<Integer>();
    }

    public IntCodeComputer(int[] intcodes, BlockingQueue<Integer> input) {
        init(intcodes, input);
    }

    public void process() {
        controlloop:
        while (true) {
            String opCodeStr = padLeftZeros(Integer.toString(memory[instructionPointer]), 5);

            String de = opCodeStr.substring(3);
            char c = opCodeStr.substring(2, 3).charAt(0);
            char b = opCodeStr.substring(1, 2).charAt(0);
            char a = opCodeStr.substring(0, 1).charAt(0);

            switch (de) {
                case "01":
                    handle_add(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b), memory[instructionPointer + 3]);
                    break;
                case "02":
                    handle_multiply(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b), memory[instructionPointer + 3]);
                    break;
                case "03":
                    handle_readInt(memory[instructionPointer + 1]);
                    break;
                case "04":
                    handle_outputInt(memory[instructionPointer + 1]);
                    break;
                case "05":
                    handle_jumpIfTrue(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b));
                    break;
                case "06":
                    handle_jumpIfFalse(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b));
                    break;
                case "07":
                    handle_LessThen(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b), memory[instructionPointer + 3]);
                    break;
                case "08":
                    handle_Equals(getValue(memory[instructionPointer + 1], c), getValue(memory[instructionPointer + 2], b), memory[instructionPointer + 3]);
                    break;
                case "99":
                    break controlloop;
            }
        }
    }

    private void handle_add(int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[ADD] %d + %d, store @ %d", val1, val2, resultPos));
        memory[resultPos] = val1 + val2;

        instructionPointer += 4; // advance 4 steps.
    }

    private void handle_multiply(int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[MULTIPLY] %d * %d, store @ %d", val1, val2, resultPos));
        memory[resultPos] = val1 * val2;

        instructionPointer += 4; // advance 4 steps.
    }

    private void handle_readInt(int resultPos) {
        try {
            if (TRACE)
                System.out.println(String.format("[INPUT] Providing value '%d', and storing it at %d", inputvalue.peek(), resultPos));
            memory[resultPos] = inputvalue.poll(10, TimeUnit.SECONDS);

            instructionPointer += 2; // advance 2 steps.
        } catch (NullPointerException e) {
            throw new RuntimeException("Unable to read from input-queue. Is it null, or depleted?", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("Aborted while reading/waiting on input", e);
        }
    }

    private void handle_outputInt(int dataPos1) {
        lastOutputValue = memory[dataPos1];
        outputvalue.offer(lastOutputValue);
        if (TRACE)
            System.out.println(String.format("[OUTPUT] value '%s'.", lastOutputValue));
        instructionPointer += 2; // advance 2 steps.
    }

    private void handle_jumpIfTrue(int param1, int param2) {
        if (TRACE)
            System.out.println(String.format("[jump-if-true] if %d != 0, go-to %d.", param1, param2));
        if (param1 != 0) {
            instructionPointer = param2;
        } else {
            instructionPointer += 3; // Do nothing else, but advance 3 steps.
        }
    }

    private void handle_jumpIfFalse(int param1, int param2) {
        if (TRACE)
            System.out.println(String.format("[jump-if-false] if %d == 0, go-to %d.", param1, param2));
        if (param1 == 0) {
            instructionPointer = param2;
        } else {
            instructionPointer += 3; // Do nothing else, but advance 3 steps.
        }
    }

    private void handle_LessThen(int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[less-then] if %d < %d, write '1' at %d, else write '0'.", val1, val2));

        if (val1 < val2) {
            memory[resultPos] = 1;
        } else {
            memory[resultPos] = 0;
        }
        instructionPointer += 4; // advance 4 steps.
    }

    private void handle_Equals(int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[equals] if %d == %d, write '1' at %d, else write '0'.", val1, val2, resultPos));

        if (val1 == val2) {
            memory[resultPos] = 1;
        } else {
            memory[resultPos] = 0;
        }
        instructionPointer += 4; // advance 4 steps.
    }

    public int getLastOutputValue() {
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

    private int getValue(int param, char paramMode) {
        if (paramMode == '1') {
            return param;
        } else {
            return memory[param];
        }
    }

    public void enableTracing() {
        this.TRACE = true;
    }

    public BlockingQueue<Integer> getOutput() {
        return this.outputvalue;
    }

    public BlockingQueue<Integer> getInput() {
        return this.inputvalue;
    }

    public int compute() {
        System.out.println("START COMPUTE!");
        process();
        System.out.println("END COMPUTE!");
        return getLastOutputValue();
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("START CALL!");
        process();
        System.out.println("END CALL!");
        return getLastOutputValue();
    }

    @Override
    public void run() {
        System.out.println("START RUN!");
        process();
        System.out.println("END RUN!");
    }
}