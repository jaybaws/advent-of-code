package org.jaybaws.adventofcode.y2020.day15;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D15PuzzleTest {

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(436, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "0", "3", "6" })).solution1());
    }

    @Test
    public void part1b_sample_validationTest() {
        Assert.assertEquals(1, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "1", "3", "2" })).solution1());
    }

    @Test
    public void part1c_sample_validationTest() {
        Assert.assertEquals(10, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "2", "1", "3" })).solution1());
    }

    @Test
    public void part1d_sample_validationTest() {
        Assert.assertEquals(27, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "1", "2", "3" })).solution1());
    }

    @Test
    public void part1e_sample_validationTest() {
        Assert.assertEquals(78, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "2", "3", "1" })).solution1());
    }

    @Test
    public void part1f_sample_validationTest() {
        Assert.assertEquals(438, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "3", "2", "1" })).solution1());
    }

    @Test
    public void part1g_sample_validationTest() {
        Assert.assertEquals(1836, (int) new Y2020D15Puzzle(Arrays.asList(new String[] { "3", "1", "2" })).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(1259, (int) new Y2020D15Puzzle().solution1());
    }

    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(175594, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "0", "3", "6" })).solution2());
    }

    @Test
    public void part2b_sample_validationTest() {
        Assert.assertEquals(2578, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "1", "3", "2" })).solution2());
    }

    @Test
    public void part2c_sample_validationTest() {
        Assert.assertEquals(3544142, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "2", "1", "3" })).solution2());
    }

    @Test
    public void part2d_sample_validationTest() {
        Assert.assertEquals(261214, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "1", "2", "3" })).solution2());
    }

    @Test
    public void part2e_sample_validationTest() {
        Assert.assertEquals(6895259, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "2", "3", "1" })).solution2());
    }

    @Test
    public void part2f_sample_validationTest() {
        Assert.assertEquals(18, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "3", "2", "1" })).solution2());
    }

    @Test
    public void part2g_sample_validationTest() {
        Assert.assertEquals(362, (long) new Y2020D15Puzzle(Arrays.asList(new String[] { "3", "1", "2" })).solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(689, (long) new Y2020D15Puzzle().solution2());
    }

}