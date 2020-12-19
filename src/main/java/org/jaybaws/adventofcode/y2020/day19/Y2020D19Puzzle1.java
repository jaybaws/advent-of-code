package org.jaybaws.adventofcode.y2020.day19;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.*;

public class Y2020D19Puzzle1 extends BasePuzzle {

    public static final boolean TRACE = true;

    public Y2020D19Puzzle1(List<String> altInput) {
        super(altInput);
    }

    private Map<String, String> rules;
    private List<String> messages;

    @Override
    protected void prepare() {
        rules = new HashMap<String, String>();
        messages = new ArrayList<String>();

        for (String line : puzzleInput) {
            if (line.contains(":")) {
                String[] parts = line.split(": ");
                rules.put(parts[0], parts[1]);
            } else {
                if (!line.equals(""))
                    messages.add(line);
            }
        }
    }

    public Y2020D19Puzzle1() {
        super();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public String makeRegExp1(String s) {
        if (isNumeric(s)) {
            return makeRegExp1(rules.get(s));
        } else if (s.contains("\"")) {
            return "[" + s.charAt(1) + "]";
        } else if (s.contains(" | ")) {
            String[] parts = s.split(" \\| ");

            return String.format("(%s|%s)", makeRegExp1(parts[0]), makeRegExp1(parts[1]));
        } else if (s.contains(" ")) {
            String[] parts = s.split(" ");
            return String.format("%s%s", makeRegExp1(parts[0]), makeRegExp1(parts[1]));
        } else {
            return ""; // ?
        }
    }

    public String makeRegExp1(int n) {
        return "^" + makeRegExp1(String.valueOf(n)) + "$";
    }

    @Override
    public Integer solution1() {
        String rule = makeRegExp1(0);
        int count = 0;
        for (String msg : messages) {
            if (msg.matches(rule))
                count++;
        }
        return count;
    }

    public String makeRegExp2(String s) {
        if (s.equals("8")) {
            return String.format("(%s)+", makeRegExp2("42"));
        } else if (s.equals("11")) {
            return makeRegExp2("42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31 | 42 42 42 42 42 42 31 31 31 31 31 31 | 42 42 42 42 42 42 42 31 31 31 31 31 31 31 | 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 | 42 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 31 | 42 42 42 42 42 42 42 42 42 42 31 31 31 31 31 31 31 31 31 31");
            // return String.format("((%s)+)((%s)+)", makeRegExp2("42"), makeRegExp2("31")); // but we need to make sure both groups are equally long! how???
        } else if (isNumeric(s)) {
            return makeRegExp2(rules.get(s));
        } else if (s.contains("\"")) {
            return "[" + s.charAt(1) + "]";
        } else if (s.contains(" | ")) {
            String[] parts = s.split(" \\| ");
            StringBuilder sb = new StringBuilder();
            sb.append("(" + makeRegExp2(parts[0]));
            for (int i = 1; i < parts.length; i++) {
                sb.append("|" + makeRegExp2(parts[i]));
            }
            sb.append(")");
            // return String.format("(%s|%s)", makeRegExp2(parts[0]), makeRegExp2(parts[1]));
            return sb.toString();
        } else if (s.contains(" ")) {
            String[] parts = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (String part : parts)
                sb.append(makeRegExp2(part));
            return sb.toString(); // String.format("%s%s", makeRegExp2(parts[0]), makeRegExp2(parts[1]));
        } else {
            return ""; // ?
        }
    }

    public String makeRegExp2(int n) {
        return "^" + makeRegExp2(String.valueOf(n)) + "$";
    }

    @Override
    public Integer solution2() {
        String rule = makeRegExp2(0);
        int count = 0;
        for (String msg : messages) {
            if (msg.matches(rule))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D19Puzzle1();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));

    }
}