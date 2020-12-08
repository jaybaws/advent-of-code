package org.jaybaws.adventofcode.y2020.day8;
import java.math.BigInteger;

public class Nop extends AbstractInstruction {

    private BigInteger arg;

    public Nop(BigInteger arg) {
        this.arg = arg;
    }

    @Override
    protected BigInteger doIt() {
        return BigInteger.ONE;
    }

    @Override
    public String toString() {
        return "nop " + arg.toString();
    }

}