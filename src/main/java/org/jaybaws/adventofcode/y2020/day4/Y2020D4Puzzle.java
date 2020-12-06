package org.jaybaws.adventofcode.y2020.day4;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2020D4Puzzle extends BasePuzzle {

    /*
     * @TODO: store a validation 'function' inside the enums. So we can Enum.fromString(prop-key) and apply the validation function generically?
     */

    private List<Properties> paswords;

    public Y2020D4Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D4Puzzle() { super(); }

    @Override
    public Object solution1() {
        return null; // @TODO
    }

    @Override
    public Object solution2() {
        return null; // @TODO
    }

    @Override
    public void prepare() {
        paswords = new ArrayList<Properties>();
        // @TODO
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D4Puzzle();

        System.out.println(String.format("Question 1? Well, this: %d.", puzzle.solution1())); // @TODO
        System.out.println(String.format("Question 2? Well, this: %d.", puzzle.solution2())); // @TODO

    }
}
