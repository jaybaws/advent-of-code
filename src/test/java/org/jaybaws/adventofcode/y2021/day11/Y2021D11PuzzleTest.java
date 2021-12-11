package org.jaybaws.adventofcode.y2021.day11;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D11PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(1656, (long) new Y2021D11Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(195, (long) new Y2021D11Puzzle(sample1).solution2());
    }

}