package org.jaybaws.adventofcode.y2021.day2;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2021D2Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D2Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2021D2Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        long h = 0;
        long d = 0;

        for (String line : this.puzzleInput) {
            String[] items = line.split(" ");
            String instruction = items[0];
            int distance = Integer.parseInt(items[1]);

            switch (instruction) {
                case "forward":
                    h += distance;
                    break;
                case "up":
                    d -= distance;
                    break;
                case "down":
                    d += distance;
                    break;
            }
        }

        return h * d;
    }

    @Override
    public Object solution2() {
        long h = 0;
        long d = 0;
        long aim = 0;

        for (String line : this.puzzleInput) {
            String[] items = line.split(" ");
            String instruction = items[0];
            int distance = Integer.parseInt(items[1]);

            switch (instruction) {
                case "forward":
                    h += distance;
                    d += aim * distance;
                    break;
                case "up":
                    aim -= distance;
                    break;
                case "down":
                    aim += distance;
                    break;
            }
        }

        return h * d;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D2Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}