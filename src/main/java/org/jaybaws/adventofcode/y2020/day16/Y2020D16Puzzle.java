package org.jaybaws.adventofcode.y2020.day16;
import org.jaybaws.adventofcode.*;
import java.util.List;

public class Y2020D16Puzzle extends BasePuzzle {

    public static final boolean TRACE = true;

    public Y2020D16Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // TODO!
    }

    public Y2020D16Puzzle() {
        super();
    }

    @Override
    public Integer solution1() {
        return null; // @TODO!
    }


    @Override
    public Long solution2() {
        return null; // @TODO
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D16Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));
    }

}