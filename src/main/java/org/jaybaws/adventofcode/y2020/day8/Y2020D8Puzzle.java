package org.jaybaws.adventofcode.y2020.day8;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;

import java.util.List;

public class Y2020D8Puzzle extends BasePuzzle {

    public Y2020D8Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D8Puzzle() { super(); }

    protected List<List<String>> groups;

    @Override
    protected void prepare() {
        // @TODO!
    }

    @Override
    public Integer solution1() {
        return null; // @TODO!
    }

    @Override
    public Integer solution2() {
        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D8Puzzle();

        System.out.println(String.format("<<Question 1>>? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("<<Question 2>>? Well, this: %d.", puzzle.solution2()));
    }

}