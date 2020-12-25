package org.jaybaws.adventofcode.y2020.day25;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2020D25Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D25Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2020D25Puzzle() {
        super();
    }

    public static long transform(long subjectNumber, long loopSize) {
        long result = 1;
        for (long l = 1; l <= loopSize; l++) {
            result *= subjectNumber;
            result = result % 20201227;
        }

        return result;
    }

    public static int findLoopSize(long key, long subjectNumber) {
        int value = 1;

        int size = 0;
        while (value != key) {
            value *= subjectNumber;
            value %= 20201227;
            size++;
        }

        return size;
    }

    @Override
    public Long solution1() {
        long pkey1 = Long.parseLong(puzzleInput.get(0));
        long loopSize1 = findLoopSize(pkey1, 7);
        long pkey2 = Long.parseLong(puzzleInput.get(1));
        long loopSize2 = findLoopSize(pkey2, 7);

        long encryptionKey = transform(pkey1, loopSize2);

        return encryptionKey;
    }

    @Override
    public Object solution2() {
        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D25Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}