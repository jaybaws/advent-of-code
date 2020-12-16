package org.jaybaws.adventofcode.y2020.day16;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D16PuzzleTest {

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D16Puzzle(Arrays.asList(new String[] { "a", "b", "c" })).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D16Puzzle().solution1());
    }

    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D16Puzzle(Arrays.asList(new String[] { "a", "b", "c" })).solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D16Puzzle().solution2());
    }

}