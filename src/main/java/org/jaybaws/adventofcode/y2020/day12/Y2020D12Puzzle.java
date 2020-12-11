package org.jaybaws.adventofcode.y2020.day12;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2020D12Puzzle extends BasePuzzle {

    public Y2020D12Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D12Puzzle() { super(); }

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
        prepare(); // SAFETY? ASSURE WE'RE WORKING OF FRESH INPUT, AND NOT OF Q1 OUTPUT!

        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D12Puzzle();

        System.out.println(String.format("[q1] ? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("[q2] ? Well, this: %d.", puzzle.solution2()));
    }

}