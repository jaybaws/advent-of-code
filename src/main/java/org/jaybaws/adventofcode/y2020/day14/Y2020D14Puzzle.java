package org.jaybaws.adventofcode.y2020.day14;
import org.jaybaws.adventofcode.*;
import java.util.List;

public class Y2020D14Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D14Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D14Puzzle() { super(); }

    @Override
    protected void prepare() {
        // @TODO!
    }

    @Override
    public Integer solution1() {
        return null; // @TODO
    }

    @Override
    public Integer solution2() {
        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D14Puzzle();

        System.out.println(String.format("[Q1?]\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("[Q2?]\n--> Well, this: %d.", puzzle.solution2()));
    }

}