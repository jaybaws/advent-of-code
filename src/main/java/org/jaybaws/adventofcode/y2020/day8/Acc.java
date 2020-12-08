package org.jaybaws.adventofcode.y2020.day8;
import java.math.BigInteger;
import java.util.Map;

public class Acc extends AbstractInstruction {

    private Map<String,Object> memory;
    private BigInteger arg;

    public Acc(BigInteger a, Map<String,Object> mem) {
        arg = a;
        memory = mem;
    }

    @Override
    protected BigInteger doIt() {
        BigInteger accumulator = (BigInteger) memory.get(Program.ACCUMULATOR);
        memory.replace(Program.ACCUMULATOR, accumulator.add(arg));

        return BigInteger.ONE;
    }

    @Override
    public String toString() {
        return "acc " + arg.toString();
    }

}