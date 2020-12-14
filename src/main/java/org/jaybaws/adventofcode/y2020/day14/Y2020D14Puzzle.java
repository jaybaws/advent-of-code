package org.jaybaws.adventofcode.y2020.day14;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;
import java.math.BigInteger;
import java.util.*;

public class Y2020D14Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D14Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D14Puzzle() { super(); }

    private String mask;
    private Map<BigInteger, BigInteger> mem;

    @Override
    protected void prepare() {
        mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
        mem = new HashMap<BigInteger, BigInteger>();
    }

    private void write(BigInteger address, BigInteger value, String mask) {
        BigInteger targetValue = BitMask.apply(mask, value);

        if (TRACE)
            System.out.println(String.format("write:  %s\nvalue:  %s  (decimal: %d)\nmask:   %s\nresult: %s  (decimal: %d)\n\n", address, BitMask.toBinary(value, 36), value, mask, BitMask.toBinary(targetValue, 36), targetValue));

        if (mem.containsKey(address)) {
            mem.replace(address, targetValue);
        } else {
            mem.put(address, targetValue);
        }
    }

    @Override
    public BigInteger solution1() {
        prepare();

        for (String line : puzzleInput) {
            String[] parts = line.split(" = ");
            if (parts[0].equals("mask")) {
                mask = parts[1];

                if (TRACE)
                    System.out.println(String.format("Set mask to '%s'.", mask));

            } else if (parts[0].startsWith("mem[")){
                BigInteger address = new BigInteger(StringUtils.substringBetween(parts[0], "[", "]"));
                BigInteger value = new BigInteger(parts[1]);
                write(address, value, mask);
            } else {
                throw new IllegalArgumentException(String.format("Instruction '%s' not understood!", line));
            }
        }

        BigInteger output = BigInteger.ZERO;
        for (BigInteger val : mem.values()) {
            output = output.add(val);
        }

        return output;
    }

    @Override
    public BigInteger solution2() {
        prepare();

        for (String line : puzzleInput) {
            String[] parts = line.split(" = ");
            if (parts[0].equals("mask")) {
                mask = parts[1];

                if (TRACE)
                    System.out.println(String.format("Set mask to '%s'.", mask));

            } else if (parts[0].startsWith("mem[")){
                BigInteger address = new BigInteger(StringUtils.substringBetween(parts[0], "[", "]"));
                BigInteger value = new BigInteger(parts[1]);
                write2(address, value, mask);
            } else {
                throw new IllegalArgumentException(String.format("Instruction '%s' not understood!", line));
            }
        }

        BigInteger output = BigInteger.ZERO;
        for (BigInteger val : mem.values()) {
            output = output.add(val);
        }

        return output;
    }

    private void write2(BigInteger address, BigInteger value, String mask) {
        List<BigInteger> addresses = BitMask.determineAddresses(mask, address);
        for (BigInteger a : addresses) {
            if (mem.containsKey(a)) {
                mem.replace(a, value);
            } else {
                mem.put(a, value);
            }
        }
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D14Puzzle();

        System.out.println(String.format("What is the sum of all values left in memory after it completes?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the sum of all values left in memory after it completes?\n--> Well, this: %d.", puzzle.solution2()));
    }

}