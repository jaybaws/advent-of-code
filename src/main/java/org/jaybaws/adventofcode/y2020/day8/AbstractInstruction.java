package org.jaybaws.adventofcode.y2020.day8;

import java.math.BigInteger;

public abstract class AbstractInstruction implements Instruction {

    private BigInteger executionCount = BigInteger.ZERO;

    public final BigInteger execute() {
        executionCount = executionCount.add(BigInteger.ONE);
        return doIt();
    }

    public final BigInteger getExecutionCount() {
        return executionCount;
    }

    protected abstract BigInteger doIt();

}