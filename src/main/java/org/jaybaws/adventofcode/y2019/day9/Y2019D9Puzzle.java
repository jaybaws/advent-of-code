package org.jaybaws.adventofcode.y2019.day9;
import org.jaybaws.adventofcode.*;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Y2019D9Puzzle extends BasePuzzle {

    public Y2019D9Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2019D9Puzzle() {
        super();
    }

    private IntCodeComputer computer;

    private BlockingQueue<BigInteger> input;

    @Override
    protected void prepare() {
        input = new LinkedBlockingQueue<BigInteger>();
        computer = IntCodeComputer.fromString(puzzleInput.get(0), input, null);
    }

    public void addInput(BigInteger in) {
        this.input.add(in);
    }

    @Override
    public Integer solution1() {
        return null; // @TODO
    }

    @Override
    public String solution2() {
        return null; // @TODO
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2019D9Puzzle();

        System.out.println(String.format("<Question 1>? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("<Question 2>? Well, this:\n\n%s", puzzle.solution2()));
    }

}