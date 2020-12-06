package org.jaybaws.adventofcode.y2020.day6;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2020D6Puzzle extends BasePuzzle {

    public Y2020D6Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D6Puzzle() { super(); }

    protected List<List<String>> groups;

    @Override
    protected void prepare() {
        groups = new ArrayList<List<String>>();
        List<String> answers = new ArrayList<String>();

        /* Group the individual answers into groups of answer lines. */
        for (String answer : puzzleInput) {
            if (answer.equals("")) {
                groups.add(answers);
                answers = new ArrayList<String>();
            } else {
                answers.add(answer);
            }
        }

        /* The last answer is probably not followed by an \r\n! So, we must add
           all staged answers that haven't yet been added!! */
        if (answers.size() > 0)
            groups.add(answers);
    }

    @Override
    public Integer solution1() {
        int totalCount = 0;

        /* Count all unique answers per group */
        for (List<String> group : groups) {
            List<Character> chars = new ArrayList<Character>();
            for (String answer : group) {
                for (char c : answer.toCharArray()) {
                    chars.add(c);
                }
            }

            Set<Character> uniqueChars = new HashSet<>(chars);
            totalCount += uniqueChars.size(); // Sum the count of the groups
        }

        return totalCount; // return the total count: the sum of count per group.
    }

    @Override
    public Integer solution2() {
        int totalCount = 0;

        for (List<String> group : groups) {
            String allGroupAnswers = String.join("", group);
            Set<Character> uniqueGroupAnswers = convertToSet(allGroupAnswers.toCharArray());

            int subCount = 0;
            for (char c : uniqueGroupAnswers) {
                int answerCount = StringUtils.countMatches(allGroupAnswers, c);
                if (answerCount == group.size())
                    subCount++;
            }

            totalCount += subCount;
        }

        return totalCount;

    }

    public static Set convertToSet(char[] charArray) {

        // Result hashset
        Set resultSet = new HashSet();

        for (int i = 0; i < charArray.length; i++) {
            resultSet.add(new Character(charArray[i]));
        }

        // Return result
        return resultSet;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D6Puzzle();

        System.out.println(String.format("What is the sum of those counts? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the sum of those counts? Well, this: %d.", puzzle.solution2()));

    }

}