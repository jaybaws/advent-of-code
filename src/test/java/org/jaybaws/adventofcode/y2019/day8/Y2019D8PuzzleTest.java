package org.jaybaws.adventofcode.y2019.day8;
import junit.framework.Assert;
import org.jaybaws.adventofcode.*;
import org.junit.Test;
import java.util.Arrays;

public class Y2019D8PuzzleTest {

    static final String[] sample = new String[] { "123456789012" };

    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2019D8Puzzle(3, 2, Arrays.asList(sample));
        Assert.assertEquals(1, p.solution1());
    }

    @Test
    public void part1_actualTest() {
        Puzzle p = new Y2019D8Puzzle();
        Assert.assertEquals(2286, p.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.fail();
    }

    @Test
    public void part2_actualTest() {
        Assert.fail();
    }
}