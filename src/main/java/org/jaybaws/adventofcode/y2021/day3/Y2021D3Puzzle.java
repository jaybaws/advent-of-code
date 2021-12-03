package org.jaybaws.adventofcode.y2021.day3;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class Y2021D3Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D3Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2021D3Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        String gamma = "";
        String epsilon = "";

        int bits = this.puzzleInput.get(0).length();

        for (int b = 0; b < bits; b++) {
            int ones = 0;
            int zeroes = 0;

            for (String line : this.puzzleInput) {
                String val = line.substring(b, b + 1);
                switch (val) {
                    case "0":
                        zeroes++;
                        break;
                    case "1":
                        ones++;
                        break;
                }
            }

            if (zeroes > ones) {
                gamma += "0";
                epsilon += "1";
            } else {
                gamma += "1";
                epsilon += "0";
            }
        }

        System.out.println("Gamma: "+ gamma);
        System.out.println("Epsilon: "+ epsilon);

        return Long.parseLong(gamma, 2) * Long.parseLong(epsilon, 2);
    }

    private Long drillDown(List<String> inputReport, boolean mostCommonMode) {
        int bits = inputReport.get(0).length();
        List<String> report = new ArrayList<String>(inputReport);
        for (int b = 0; b < bits; b++) {
            int ones = 0;
            int zeroes = 0;

            for (String line : report) {
                String val = line.substring(b, b + 1);
                switch (val) {
                    case "0":
                        zeroes++;
                        break;
                    case "1":
                        ones++;
                        break;
                }
            }

            String mostCommon = (zeroes > ones) ? "0":"1";
            String leastCommon = (zeroes < ones) ? "0":"1";

            String keep;
            if (mostCommonMode) {
                keep = (ones >= zeroes) ? "1":"0";
            } else {
                keep = (zeroes <= ones) ? "0":"1";
            }

            List<String> newReport = new ArrayList<String>();
            for (String line : report) {
                String val = line.substring(b, b + 1);
                if (val.equals(keep))
                    newReport.add(line);
            }

            if (newReport.size() == 1) {
                return Long.parseLong(newReport.get(0), 2);
            } else {
                report = newReport;
            }
        }

        return null; // This means nothing matches, which would be funny. A gold old-fashioned crash on a NullPointer would totally be in place :)
    }

    @Override
    public Object solution2() {
        long oxygenGeneratorRating = drillDown(this.puzzleInput, true);
        long CO2ScrubberRating = drillDown(this.puzzleInput, false);

        long lifeSupportRate = oxygenGeneratorRating * CO2ScrubberRating;
        return lifeSupportRate;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D3Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}