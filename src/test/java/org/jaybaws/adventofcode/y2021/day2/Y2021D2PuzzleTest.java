package org.jaybaws.adventofcode.y2021.day2;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Y2021D2PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(150, (long) new Y2021D2Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(900, (long) new Y2021D2Puzzle(sample1).solution2());
    }

}
