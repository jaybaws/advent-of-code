package org.jaybaws.adventofcode.y2020.day17;
import junit.framework.Assert;
import org.junit.Test;
import javax.vecmath.Point3d;
import java.util.Arrays;

public class Y2020D17PuzzleTest {

    static final String[] sample1 = new String[] { ".#.", "..#", "###" };

    @Test
    public void coordinateTest() {
        Assert.assertEquals(26, Y2020D17Puzzle1.surroundingPoints(new Point3d(0,0,0)).size());
    }

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(112, (int) new Y2020D17Puzzle1(Arrays.asList(sample1)).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(448, (int) new Y2020D17Puzzle1().solution1());
    }

    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(848, (int) new Y2020D17Puzzle2(Arrays.asList(sample1)).solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(2400, (int) new Y2020D17Puzzle2().solution2());
    }

}