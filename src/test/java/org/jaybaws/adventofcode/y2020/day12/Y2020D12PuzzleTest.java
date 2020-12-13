package org.jaybaws.adventofcode.y2020.day12;
import org.jaybaws.adventofcode.*;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D12PuzzleTest {

    static final String[] sample = new String[] { "F10", "N3", "F7", "R90", "F11" };

    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2020D12Puzzle(Arrays.asList(sample));
        Assert.assertEquals(25, p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D12Puzzle();
        Assert.assertEquals(796, p.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D12Puzzle(Arrays.asList(sample));
        Assert.assertEquals(286, p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D12Puzzle();
        Assert.assertEquals(39446, p.solution2());
    }

}