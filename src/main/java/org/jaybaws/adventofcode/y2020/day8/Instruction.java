package org.jaybaws.adventofcode.y2020.day8;
import java.math.BigInteger;
import java.util.Map;

public interface Instruction {

    public BigInteger execute();
    public BigInteger getExecutionCount();

    public static Instruction fromString(String str, Map<String, Object> memory) {
        String[] items = str.split(" ");

        String op = items[0];
        BigInteger arg = new BigInteger(items[1]);

        switch (op) {
            case "nop":
                return new Nop(arg);
            case "acc":
                return new Acc(arg, memory);
            case "jmp":
                return new Jump(arg);
            default:
                throw new IllegalArgumentException(String.format("Unable to parse '%s' as a proper operation!", str));
        }
    }
}
