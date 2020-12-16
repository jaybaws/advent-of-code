package org.jaybaws.adventofcode.y2020.day16;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D16PuzzleTest {

    static final String[] sample1 = new String[] {
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50",
            "",
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12" };

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(71, (int) new Y2020D16Puzzle(Arrays.asList(sample1)).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(23122, (int) new Y2020D16Puzzle().solution1());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D16Puzzle();
        p.solution1(); // Prepare the data.

        Assert.assertEquals((long) Long.valueOf("362974212989"), (long) p.solution2());
    }

}