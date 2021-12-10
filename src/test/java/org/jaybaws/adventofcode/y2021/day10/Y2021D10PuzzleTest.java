package org.jaybaws.adventofcode.y2021.day10;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D10PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
    } );

    @Test
    public void functionsTest() {
        Assert.assertTrue(Y2021D10Puzzle.isClosingTag(']'));
        Assert.assertTrue(Y2021D10Puzzle.isClosingTag('}'));
        Assert.assertTrue(Y2021D10Puzzle.isClosingTag('>'));
        Assert.assertTrue(Y2021D10Puzzle.isClosingTag(')'));
        Assert.assertFalse(Y2021D10Puzzle.isClosingTag('['));
        Assert.assertFalse(Y2021D10Puzzle.isClosingTag('{'));
        Assert.assertFalse(Y2021D10Puzzle.isClosingTag('<'));
        Assert.assertFalse(Y2021D10Puzzle.isClosingTag('('));

        Assert.assertFalse(Y2021D10Puzzle.isOpeningTag(']'));
        Assert.assertFalse(Y2021D10Puzzle.isOpeningTag('}'));
        Assert.assertFalse(Y2021D10Puzzle.isOpeningTag('>'));
        Assert.assertFalse(Y2021D10Puzzle.isOpeningTag(')'));
        Assert.assertTrue(Y2021D10Puzzle.isOpeningTag('['));
        Assert.assertTrue(Y2021D10Puzzle.isOpeningTag('{'));
        Assert.assertTrue(Y2021D10Puzzle.isOpeningTag('<'));
        Assert.assertTrue(Y2021D10Puzzle.isOpeningTag('('));

        Assert.assertTrue(Y2021D10Puzzle.isCorrectlyClosed('(', ')'));
        Assert.assertFalse(Y2021D10Puzzle.isCorrectlyClosed(')', '('));
        Assert.assertFalse(Y2021D10Puzzle.isCorrectlyClosed('(', ']'));

    }

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(26397, (long) new Y2021D10Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(288957, (long) new Y2021D10Puzzle(sample1).solution2());
    }

}