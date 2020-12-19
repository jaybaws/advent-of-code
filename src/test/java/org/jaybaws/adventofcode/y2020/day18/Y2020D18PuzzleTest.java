package org.jaybaws.adventofcode.y2020.day18;
import junit.framework.Assert;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Arrays;

public class Y2020D18PuzzleTest {

    static final String[] sample1 = new String[] {  };

    @Test
    public void simple_a_Test() {
        Assert.assertEquals(71, (long) Y2020D18Puzzle.calc1("1 + 2 * 3 + 4 * 5 + 6"));
    }

    @Test
    public void simple_b_Test() {
        Assert.assertEquals(26, (long) Y2020D18Puzzle.calc1("2 * 3 + (4 * 5)"));
    }

    @Test
    public void simple_c_Test() {
        Assert.assertEquals(437, (long) Y2020D18Puzzle.calc1("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
    }

    @Test
    public void simple_d_Test() {
        Assert.assertEquals(12240, (long) Y2020D18Puzzle.calc1("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
    }

    @Test
    public void simple_e_Test() {
        Assert.assertEquals(13632, (long) Y2020D18Puzzle.calc1("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));
    }


    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(new BigInteger("30753705453324"),new Y2020D18Puzzle().solution1());
    }


    @Test
    public void simple_f_Test() {
        Assert.assertEquals(51, (long) Y2020D18Puzzle.calc1("1+(2*3)+(4*11)"));
    }


    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(231, (long) new Y2020D18Puzzle(Arrays.asList(new String[] { "1 + 2 * 3 + 4 * 5 + 6" })).solution2());
    }

    @Test
    public void part2b_sample_validationTest() {
        Assert.assertEquals(51, (long) new Y2020D18Puzzle(Arrays.asList(new String[] { "1 + (2 * 3) + (4 * (5 + 6))" })).solution2());
    }

    @Test
    public void part2c_sample_validationTest() {
        Assert.assertEquals(46, (long) new Y2020D18Puzzle(Arrays.asList(new String[] { "2 * 3 + (4 * 5)" })).solution2());
    }


    @Test
    public void part2_actual_validationTest() {

        Assert.assertEquals(244817530095503l, (long) new Y2020D18Puzzle().solution2());
    }

}