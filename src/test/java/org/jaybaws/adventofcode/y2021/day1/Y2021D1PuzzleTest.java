package org.jaybaws.adventofcode.y2021.day1;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Y2021D1PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "199",
            "200",
            "208",
            "210",
            "200",
            "207",
            "240",
            "269",
            "260",
            "263"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(7, (long) new Y2021D1Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(5, (long) new Y2021D1Puzzle(sample1).solution2());
    }

}
