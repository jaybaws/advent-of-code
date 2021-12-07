package org.jaybaws.adventofcode.y2021.day6;
import junit.framework.Assert;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Y2021D6PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "3,4,3,1,2"
    } );

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(26, (long) new Y2021D6Puzzle(sample1).solution1(18));
    }


    @Test
    public void part1b_sample_validationTest() {
        Assert.assertEquals(5934, (long) new Y2021D6Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(new BigInteger("26984457539"), new Y2021D6Puzzle(sample1).solution2());
    }

}