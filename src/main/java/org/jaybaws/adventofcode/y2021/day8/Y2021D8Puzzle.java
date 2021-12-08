package org.jaybaws.adventofcode.y2021.day8;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;

import java.util.*;

public class Y2021D8Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D8Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {

    }

    public Y2021D8Puzzle() {
        super();
    }

    @Override
    public String solution1() {
        return "Just use Excel or Notepad++ you bloody idiot!";
    }

    public static String sort(String inputString) {

        Character tempArray[]
                = new Character[inputString.length()];

        for (int i = 0; i < inputString.length(); i++) {
            tempArray[i] = inputString.charAt(i);
        }

        Arrays.sort(tempArray, new Comparator<Character>() {

            @Override
            public int compare(Character c1, Character c2)
            {
                // Ignoring case
                return Character.compare(
                        Character.toLowerCase(c1),
                        Character.toLowerCase(c2));
            }
        });

        StringBuilder sb
                = new StringBuilder(tempArray.length);

        for (Character c : tempArray)
            sb.append(c.charValue());

        return sb.toString();
    }

    private int overlap(String input, String reference) {
        int total = 0;

        for (char c : input.toCharArray()) {
            if (reference.contains(String.valueOf(c)))
                total++;
        }

        return total;
    }

    @Override
    public Long solution2() {
        long total = 0;

        for (String line : this.puzzleInput) {
            String[] lineItems = line.split(" \\| ");
            String[] signalPatterns = lineItems[0].split(" ");
            String[] outputValues = lineItems[1].split(" ");

            Map<String, String> meanings = new HashMap<String, String>();
            Map<String, String> values = new HashMap<String, String>();

            for (String signalPattern : signalPatterns) {
                if (signalPattern.length() == 2) {
                    meanings.put("1", signalPattern);
                    values.put(sort(signalPattern), "1");
                } else if (signalPattern.length() == 3) {
                    meanings.put("7", signalPattern);
                    values.put(sort(signalPattern), "7");
                } else if (signalPattern.length() == 4) {
                    meanings.put("4", signalPattern);
                    values.put(sort(signalPattern), "4");
                } else if (signalPattern.length() == 7) {
                    meanings.put("8", signalPattern);
                    values.put(sort(signalPattern), "8");
                }
            }

            for (String signalPattern : signalPatterns) {
                if (signalPattern.length() == 5) {
                    if (overlap(signalPattern, meanings.get("1")) == 1 && overlap(signalPattern, meanings.get("4")) == 2 && overlap(signalPattern, meanings.get("7")) == 2) {
                        meanings.put("2", signalPattern);
                        values.put(sort(signalPattern), "2");
                    } else if (overlap(signalPattern, meanings.get("1")) == 1 && overlap(signalPattern, meanings.get("4")) == 3 && overlap(signalPattern, meanings.get("7")) == 2) {
                        meanings.put("5", signalPattern);
                        values.put(sort(signalPattern), "5");
                    } else if (overlap(signalPattern, meanings.get("1")) == 2 && overlap(signalPattern, meanings.get("4")) == 3 && overlap(signalPattern, meanings.get("7")) == 3) {
                        meanings.put("3", signalPattern);
                        values.put(sort(signalPattern), "3");
                    }
                } else if (signalPattern.length() == 6) {
                    if (overlap(signalPattern, meanings.get("4")) == 4) {
                        meanings.put("9", signalPattern);
                        values.put(sort(signalPattern), "9");
                    } else if (overlap(signalPattern, meanings.get("4")) == 3 && overlap(signalPattern, meanings.get("1")) == 2) {
                        meanings.put("0", signalPattern);
                        values.put(sort(signalPattern), "0");
                    } else if (overlap(signalPattern, meanings.get("4")) == 3 && overlap(signalPattern, meanings.get("1")) == 1) {
                        meanings.put("6", signalPattern);
                        values.put(sort(signalPattern), "6");
                    }
                }
            }

            String output = "";
            for (String outputValue : outputValues) {
                output += values.get(sort(outputValue));
            }

            total += Long.valueOf(output);

        }
        return total;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D8Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}