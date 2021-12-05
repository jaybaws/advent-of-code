package org.jaybaws.adventofcode.y2021.day5;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;
import java.util.stream.Stream;

public class Y2021D5Puzzle extends BasePuzzle {

    public static final String splitRegExp = "(,|\\s\\->\\s)";
    public static final boolean TRACE = false;

    public Y2021D5Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {

    }

    public Y2021D5Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        Map map = new Map();
        for (String line : this.puzzleInput) {

            int[] c = Stream.of(line.split(splitRegExp))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            map.addLine(c[0], c[1], c[2], c[3], true);
        }
        return map.getTwoOrMoreCrossing();
    }

    @Override
    public Object solution2() {
        Map map = new Map();
        for (String line : this.puzzleInput) {

            int[] c = Stream.of(line.split(splitRegExp))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            map.addLine(c[0], c[1], c[2], c[3], false);
        }
        return map.getTwoOrMoreCrossing();
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D5Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}