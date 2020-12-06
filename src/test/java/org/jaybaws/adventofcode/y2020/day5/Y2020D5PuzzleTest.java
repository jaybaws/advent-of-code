package org.jaybaws.adventofcode.y2020.day5;
import junit.framework.Assert;
import org.junit.Test;

public class Y2020D5PuzzleTest {

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(567, new Seat("BFFFBBFRRR").getSeatId());
        Assert.assertEquals(119, new Seat("FFFBBBFRRR").getSeatId());
        Assert.assertEquals(820, new Seat("BBFFBBFRLL").getSeatId());
    }

    @Test
    public void part2_sample_validationTest() {
        // @ TODO
    }

}