package org.jaybaws.adventofcode.y2021.day13;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Y2021D13Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D13Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2021D13Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        Paper sheet = new Paper();
        List<String> instructions = new LinkedList<String>();

        for (String line : this.puzzleInput) {
            if (line.contains(",")) {
                String[] parts = line.split(",");
                sheet.dot(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
            } else if (line.contains("fold")) {
                instructions.add(line);
            }
        }

        sheet.execute(instructions.get(0));

        return sheet.visibleDots();
    }

    @Override
    public String solution2() {
        Paper sheet = new Paper();
        List<String> instructions = new LinkedList<String>();

        for (String line : this.puzzleInput) {
            if (line.contains(",")) {
                String[] parts = line.split(",");
                sheet.dot(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
            } else if (line.contains("fold")) {
                instructions.add(line);
            }
        }

        for (String instruction : instructions)
            sheet.execute(instruction);

        return sheet.toString();
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D13Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this:\n\n%s", puzzle.solution2()));
    }

}