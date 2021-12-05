package org.jaybaws.adventofcode.y2021.day5;

import junit.framework.Assert;
import org.jaybaws.adventofcode.y2021.day4.Y2021D4Puzzle;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Y2021D5PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(5, (long) new Y2021D5Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(12, (long) new Y2021D5Puzzle(sample1).solution2());
    }

    @Test
    public void blaTest() {
        Assert.assertTrue("88,177 -> 566,655".split(Y2021D5Puzzle.splitRegExp).length == 4);
    }

}