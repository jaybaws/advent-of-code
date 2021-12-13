package org.jaybaws.adventofcode.y2021.day13;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D13PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "6,10",
            "0,14",
            "9,10",
            "0,3",
            "10,4",
            "4,11",
            "6,0",
            "6,12",
            "4,1",
            "0,13",
            "10,12",
            "3,4",
            "3,0",
            "8,4",
            "1,10",
            "2,14",
            "8,10",
            "9,0",
            "",
            "fold along y=7",
            "fold along x=5"
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(17, (long) new Y2021D13Puzzle(sample1).solution1());
    }

//    @Test
//    public void part2_sample_validationTest() {
//        Assert.assertEquals(16, (long) new Y2021D13Puzzle(sample1).solution2());
//    }

}