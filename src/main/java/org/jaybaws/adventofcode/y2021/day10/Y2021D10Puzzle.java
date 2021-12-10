package org.jaybaws.adventofcode.y2021.day10;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.*;

public class Y2021D10Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    long totalSyntaxErrorScore;
    List<Long> autocompleteScores;

    public Y2021D10Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        totalSyntaxErrorScore = 0;
        autocompleteScores = new ArrayList<>();

        for (String chunks : this.puzzleInput) {
            Stack<Character> stack = new Stack<Character>();
            boolean invalid = false;

            loops:
            for (char c : chunks.toCharArray()) {
                if (isClosingTag(c)) {
                    char topOfStack = stack.peek();
                    char expected = getClosingTagFor(topOfStack);
                    if (!isCorrectlyClosed(topOfStack, c)) {
                        System.out.printf("%s - Expected %s, but found %s instead.%n", chunks, expected, c);
                        totalSyntaxErrorScore += getScore(c);
                        invalid = true;
                        break loops;
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.push(c);
                }
            }

            if (stack.size() > 0 && !invalid) {
                String completeStr = complete(stack);
                long totalAutocompleteScore = autocompleteScore(completeStr);
                autocompleteScores.add(totalAutocompleteScore);
                System.out.printf("%s - Complete by adding %s. --> %d total points.%n", chunks, completeStr, totalAutocompleteScore);
            }
        }

        Collections.sort(autocompleteScores);

    }

    public Y2021D10Puzzle() {
        super();
    }

    public static boolean isOpeningTag(char c) {
        return c == '(' || c == '<' || c == '{' || c == '[';
    }

    public static boolean isClosingTag(char c) {
        return c == ')' || c == '>' || c == '}' || c == ']';
    }

    public static char getClosingTagFor(char c) {
        switch (c) {
            case '{':
                return '}';
            case '[':
                return ']';
            case '<':
                return '>';
            case '(':
                return ')';
            default:
                return '?';
        }
    }

    public static boolean isCorrectlyClosed(char a, char b) {
        if (a == '(') {
            return (b == ')');
        } else if (a == '<') {
            return (b == '>');
        } else if (a  == '{') {
            return (b == '}');
        } else if (a == '[') {
            return (b == ']');
        } else
            return false;
    }

    public static long getScore(char c) {
        switch (c) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    public static long getCompetionScore(char c) {
        switch (c) {
            case ')':
                return 1;
            case ']':
                return 2;
            case '}':
                return 3;
            case '>':
                return 4;
            default:
                return 0;
        }
    }

    public static String complete(Stack<Character> s) {
        StringBuilder result = new StringBuilder();
        while (!s.empty()) {
            result.append(getClosingTagFor(s.pop()));
        }
        return result.toString();
    }

    public static long autocompleteScore(String s) {
        long score = 0;

        for (char c :  s.toCharArray()) {
            score *= 5;
            score += getCompetionScore(c);
        }

        return score;
    }

    @Override
    public Long solution1() {
        return totalSyntaxErrorScore;
    }

    @Override
    public Long solution2() {
        int total = autocompleteScores.size();
        int middle = (int) Math.ceil(total / 2);

        return autocompleteScores.get(middle);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D10Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}