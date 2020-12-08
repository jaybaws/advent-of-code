package org.jaybaws.adventofcode.y2020.day8;
import java.math.BigInteger;

public class Jump extends AbstractInstruction {

    private BigInteger arg;

    public Jump(BigInteger a) {
        arg = a;
    }

    @Override
    protected BigInteger doIt() {
        return arg;
    }

    @Override
    public String toString() {
        return "jmp " + arg.toString();
    }

}