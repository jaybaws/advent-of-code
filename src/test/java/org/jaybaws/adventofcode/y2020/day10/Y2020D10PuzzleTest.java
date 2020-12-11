package org.jaybaws.adventofcode.y2020.day10;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

public class Y2020D10PuzzleTest {

    public static String[] sample1 = { "16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4" };
    public static String[] sample2 = { "28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24", "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4", "2", "34", "10", "3" };

    public static String[] sample3 = { "3", "6", "9", "12" };
    public static String[] sample4 = { "3", "6", "7", "9", "12" };
    public static String[] sample5 = { "3", "6", "7", "8", "9", "12" };

    @Test
    public void part1a_sample_validationTest() {
        List<String> data = Arrays.asList(sample1);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(7*5, p.solution1());
    }

    @Test
    public void part1b_sample_validationTest() {
        List<String> data = Arrays.asList(sample2);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(22*10, p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D10Puzzle();
        Assert.assertEquals(2277, p.solution1());
    }

    @Test
    public void part2c_sample_validationTest() {
        List<String> data = Arrays.asList(sample3);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(1, p.solution2());
    }

    @Test
    public void part2d_sample_validationTest() {
        List<String> data = Arrays.asList(sample4);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(2, p.solution2());
    }
    @Test
    public void part2e_sample_validationTest() {
        List<String> data = Arrays.asList(sample5);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(4, p.solution2());
    }

    @Test
    public void part2a_sample_validationTest() {
        List<String> data = Arrays.asList(sample1);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(new BigInteger("8"), p.solution2());
    }

    @Test
    public void part2b_sample_validationTest() {
        List<String> data = Arrays.asList(sample2);
        Puzzle p = new Y2020D10Puzzle(data);
        Assert.assertEquals(new BigInteger("19208"), p.solution2());
    }


    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D10Puzzle();
        Assert.assertEquals(new BigInteger("37024595836928"), p.solution2());
    }

}