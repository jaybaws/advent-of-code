package org.jaybaws.adventofcode.y2020.day13;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Arrays;

public class Y2020D13PuzzleTest {

    static final String[] sample = new String[] { "939", "7,13,x,x,59,x,31,19" };

    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2020D13Puzzle(Arrays.asList(sample));
        Assert.assertEquals(295, p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D13Puzzle();
        Assert.assertEquals(104, p.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D13Puzzle(Arrays.asList(sample));
        Assert.assertEquals(new BigInteger("1068781"), p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D13Puzzle();
        Assert.assertEquals(new BigInteger("842186186521918"), p.solution2());
    }

    @Test
    public void jorisTest() {
        //
        Assert.assertEquals(5, Math.floorMod(-939, 59));
    }

}