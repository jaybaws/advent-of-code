package org.jaybaws.adventofcode.y2021.day7;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D7PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "16,1,2,0,4,2,7,1,2,14"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(37, (long) new Y2021D7Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(168, (long) new Y2021D7Puzzle(sample1).solution2());
    }

}