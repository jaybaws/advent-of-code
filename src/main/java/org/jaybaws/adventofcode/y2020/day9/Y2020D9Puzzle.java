package org.jaybaws.adventofcode.y2020.day9;
import org.jaybaws.adventofcode.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Y2020D9Puzzle extends BasePuzzle {

    private int preambleSize = 25;
    private List<BigInteger> series;

    public Y2020D9Puzzle(List<String> altInput, int preambleSize) {
        super(altInput);
        this.preambleSize = preambleSize;
    }

    public Y2020D9Puzzle() { super(); }

    @Override
    protected void prepare() {
        series = puzzleInput.stream().map( s -> new BigInteger(s) ).collect(Collectors.toList());
    }

    @Override
    public BigInteger solution1() {
        for (int i = preambleSize; i < series.size(); i++) {
            BigInteger val = series.get(i);
            List<BigInteger> preamble = series.subList(i - preambleSize, i);
            List<BigInteger> sums = new ArrayList<BigInteger>();
            for (BigInteger p1 : preamble) {
                for (BigInteger p2 : preamble) {
                    if (!p1.equals(p2))
                        sums.add(p1.add(p2));
                }
            }

            if (!sums.contains(val))
                return val;
        }
        return null;
    }

    public static BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public  static BigInteger sum(List<BigInteger> l) {
        return l.stream().reduce(BigInteger.ZERO, Y2020D9Puzzle::add);
    }

    @Override
    public BigInteger solution2() {
        return solution2(solution1());
    }

    public BigInteger solution2(BigInteger target) {
        for (int i = 0; i < series.size(); i++) {
            for (int j = i; j <= series.size(); j++) {

                List<BigInteger> subList = series.subList(i, j);
                BigInteger theSum = sum(subList);

                if (theSum.compareTo(target) == 0) {
                    BigInteger smallest = Collections.min(subList);
                    BigInteger largest = Collections.max(subList);

                    return smallest.add(largest);

                }
             }
        }

        return null;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D9Puzzle();

        System.out.println(String.format("What is the first number that does not have this property? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the encryption weakness in your XMAS-encrypted list of numbers? Well, this: %d.", puzzle.solution2()));
    }

}