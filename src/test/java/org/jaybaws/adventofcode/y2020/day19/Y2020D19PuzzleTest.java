package org.jaybaws.adventofcode.y2020.day19;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D19PuzzleTest {

    static final String[] sample1 = new String[] {
            "0: 1 2",
            "1: \"a\"",
            "2: 1 3 | 3 1",
            "3: \"b\"",
            "",
            "aba",
            "aab",
            "aaa",
            "bbb",
            "aab"
    };

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(3, (long) new Y2020D19Puzzle1(Arrays.asList(sample1)).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(216, (long) new Y2020D19Puzzle1().solution1());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(400, (long) new Y2020D19Puzzle1().solution2());
    }

}