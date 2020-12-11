package org.jaybaws.adventofcode.y2020.day9;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Arrays;

public class Y2020D9PuzzleTest {

    static final String[] sample1 = new String[] { "35", "20", "15", "25", "47", "40", "62", "55", "65", "95", "102", "117", "150", "182", "127", "219", "299", "277", "309", "576" };
    static final BigInteger[] addSample = new BigInteger[] { new BigInteger("100"), new BigInteger("202") };


    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2020D9Puzzle(Arrays.asList(sample1), 5);
        Assert.assertEquals(new BigInteger("127"), p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D9Puzzle();
        Assert.assertEquals(new BigInteger("1038347917"), p.solution1());
    }

    @Test
    public void addTest() {
        Assert.assertEquals(new BigInteger("302"), Y2020D9Puzzle.sum(Arrays.asList(addSample)));
    }

    @Test
    public void part2_sample_validationTest() {
        Y2020D9Puzzle p = new Y2020D9Puzzle(Arrays.asList(sample1), 5);
        Assert.assertEquals(new BigInteger("62"), p.solution2(new BigInteger("127")));
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.fail();
    }

}