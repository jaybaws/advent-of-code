package org.jaybaws.adventofcode.y2020.day25;
import junit.framework.Assert;
import org.junit.Test;
import java.util.*;

public class Y2020D25PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {

    } );

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(-1, new Y2020D25Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals(-1, new Y2020D25Puzzle().solution1()); }

    @Test
    public void part2_sample_validationTest() { Assert.assertEquals(-1, new Y2020D25Puzzle(sample1).solution2()); }

    @Test
    public void part2_actual_validationTest() { Assert.assertEquals(-1, new Y2020D25Puzzle().solution2()); }

}