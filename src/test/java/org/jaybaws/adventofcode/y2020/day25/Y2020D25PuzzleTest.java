package org.jaybaws.adventofcode.y2020.day25;
import junit.framework.Assert;
import org.junit.Test;
import java.util.*;

public class Y2020D25PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "5764801",
            "17807724"
    } );

    @Test
    public void part1a_sample_validationTest() { Assert.assertEquals(8, Y2020D25Puzzle.findLoopSize(5764801, 7)); }

    @Test
    public void part1b_sample_validationTest() { Assert.assertEquals(11, Y2020D25Puzzle.findLoopSize(17807724, 7)); }

    @Test
    public void part1c_sample_validationTest() { Assert.assertEquals(14897079, Y2020D25Puzzle.transform(17807724, 8)); }

    @Test
    public void part1d_sample_validationTest() { Assert.assertEquals(14897079, Y2020D25Puzzle.transform( 5764801, 11)); }

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(14897079, (long) new Y2020D25Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals(4968512, (long) new Y2020D25Puzzle().solution1()); }

}