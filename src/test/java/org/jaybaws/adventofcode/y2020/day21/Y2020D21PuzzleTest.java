package org.jaybaws.adventofcode.y2020.day21;
import junit.framework.Assert;
import org.jaybaws.adventofcode.*;
import org.junit.Test;

public class Y2020D21PuzzleTest {

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(-1l, (long) new Y2020D21Puzzle().solution1());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D21Puzzle();
        p.solution1(); // Prepare!
        Assert.assertEquals(-1, (long) p.solution2());
    }

}