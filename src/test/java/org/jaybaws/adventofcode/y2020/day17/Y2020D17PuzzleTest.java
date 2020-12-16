package org.jaybaws.adventofcode.y2020.day17;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D17PuzzleTest {

    static final String[] sample1 = new String[] { };

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D17Puzzle(Arrays.asList(sample1)).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D17Puzzle().solution1());
    }

    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D17Puzzle(Arrays.asList(sample1)).solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(-1, (int) new Y2020D17Puzzle().solution2());
    }

}