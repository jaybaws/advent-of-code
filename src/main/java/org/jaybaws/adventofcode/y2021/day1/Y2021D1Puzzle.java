package org.jaybaws.adventofcode.y2021.day1;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2021D1Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D1Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2021D1Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        long c = 0;
        for (int i = 1; i < this.puzzleInput.size(); i++) {
            long thisVal = Long.parseLong(this.puzzleInput.get(i));
            long prevVal = Long.parseLong(this.puzzleInput.get(i - 1));
            if (thisVal > prevVal)
                c++;
        }

        return c;
    }

    @Override
    public Object solution2() {
        long c = 0;
        for (int i = 1; i < this.puzzleInput.size() - 2; i++) {
            long thisVal = Long.parseLong(this.puzzleInput.get(i)) + Long.parseLong(this.puzzleInput.get(i + 1)) + Long.parseLong(this.puzzleInput.get(i + 2));
            long prevVal = Long.parseLong(this.puzzleInput.get(i - 1)) + Long.parseLong(this.puzzleInput.get(i - 1 + 1)) + Long.parseLong(this.puzzleInput.get(i - 1 + 2));
            if (thisVal > prevVal)
                c++;
        }

        return c;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D1Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}