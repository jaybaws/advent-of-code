package org.jaybaws.adventofcode.y2020.day24;
import junit.framework.Assert;
import org.junit.Test;
import java.util.*;

public class Y2020D24PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {  } );

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(-1, new Y2020D24Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals(-1, new Y2020D24Puzzle().solution1()); }

    @Test
    public void part2_sample_validationTest() { Assert.assertEquals(-1, new Y2020D24Puzzle(sample1).solution2()); }

    @Test
    public void part2_actual_validationTest() { Assert.assertEquals(-1, new Y2020D24Puzzle().solution2()); }

}