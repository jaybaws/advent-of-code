package org.jaybaws.adventofcode.y2020.day20;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;

public class Y2020D20PuzzleTest {

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals(14986175499719l, (long) new Y2020D20Puzzle().solution1()); }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D20Puzzle();
        p.solution1(); // Prepare!
        Assert.assertEquals(2161, (long) p.solution2());
    }

}