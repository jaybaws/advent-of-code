package org.jaybaws.adventofcode.y2021.day7;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Y2021D7Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D7Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {

    }

    public Y2021D7Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        List<Integer> numbers = Arrays.stream(this.puzzleInput.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Integer bestPosition = null;
        Long bestPositionNeededFuel = null;

        for (int pos = Collections.min(numbers); pos <= Collections.max(numbers); pos++) {
            long neededFuel = 0;
            for (Integer crab : numbers) {
                neededFuel += Math.abs(crab - pos);
            }

            if (bestPosition == null) {
                bestPosition = pos;
                bestPositionNeededFuel = neededFuel;
            } else {
                if (neededFuel < bestPositionNeededFuel) {
                    bestPosition = pos;
                    bestPositionNeededFuel = neededFuel;
                }
            }

            if (TRACE)
                System.out.println(String.format("Position %d requires %d fuel!", pos, neededFuel));
        }

        return bestPositionNeededFuel;
    }

    @Override
    public Long solution2() {
        List<Integer> numbers = Arrays.stream(this.puzzleInput.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Integer bestPosition = null;
        Long bestPositionNeededFuel = null;

        for (int pos = Collections.min(numbers); pos <= Collections.max(numbers); pos++) {
            long neededFuel = 0;
            for (Integer crab : numbers) {
                long distance = Math.abs(crab - pos);
                for (long f = 1; f <= distance; f++)
                    neededFuel += f;
            }

            if (bestPosition == null) {
                bestPosition = pos;
                bestPositionNeededFuel = neededFuel;
            } else {
                if (neededFuel < bestPositionNeededFuel) {
                    bestPosition = pos;
                    bestPositionNeededFuel = neededFuel;
                }
            }

            if (TRACE)
                System.out.println(String.format("Position %d requires %d fuel!", pos, neededFuel));
        }

        return bestPositionNeededFuel;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D7Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}