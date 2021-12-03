package org.jaybaws.adventofcode.y2021.day3;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D3PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(198, (long) new Y2021D3Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(230, (long) new Y2021D3Puzzle(sample1).solution2());
    }

}