package org.jaybaws.adventofcode.y2020.day20;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;

public class Y2020D20PuzzleTest {

    static final String[] sample1 = new String[] {  };

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D20Puzzle(Arrays.asList(sample1)).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D20Puzzle().solution1());
    }

    @Test
    public void part2_actual_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D20Puzzle().solution2());
    }

}