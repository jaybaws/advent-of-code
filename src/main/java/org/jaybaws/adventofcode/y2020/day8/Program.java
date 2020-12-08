package org.jaybaws.adventofcode.y2020.day8;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Program {

    static final boolean TRACE = true;

    static final String ACCUMULATOR = "accumulator";

    private Map<BigInteger, Instruction> instructions = new HashMap<BigInteger, Instruction>();
    private final Map<String, Object> memory = new HashMap<String, Object>();

    public Program(List<String> i) {
        memory.put(ACCUMULATOR, BigInteger.ZERO);

        BigInteger index = BigInteger.ZERO;
        Iterator<String> it = i.iterator();
        while (it.hasNext()) {
            instructions.put(index, Instruction.fromString(it.next(), memory));
            index = index.add(BigInteger.ONE);
        }
    }

    public void run(BigInteger maxDepth, boolean error) {
        BigInteger pointer = BigInteger.ZERO;

        controlloop:
        while (instructions.containsKey(pointer)) {
            Instruction i = instructions.get(pointer);
            if (i.getExecutionCount().compareTo(maxDepth) == 0) {
                if (error) {
                    throw new RuntimeException("Reached our maximum depth of loops!");
                } else
                    break controlloop;
            } else {
                pointer = pointer.add(i.execute());
            }
        }
    }

    public Object readMemory(String key) {
        return memory.get(key);
    }

}