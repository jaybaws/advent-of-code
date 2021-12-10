package org.jaybaws.adventofcode.y2021.day9;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Y2021D9PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "2199943210",
            "3987894921",
            "9856789892",
            "8767896789",
            "9899965678"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(15, (long) new Y2021D9Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(1134, (long) new Y2021D9Puzzle(sample1).solution2());
    }

    @Test
    public void equalityTest() {
        Assert.assertTrue(Objects.equals(new Coordinate(10,10), new Coordinate(10, 10)));
    }

}