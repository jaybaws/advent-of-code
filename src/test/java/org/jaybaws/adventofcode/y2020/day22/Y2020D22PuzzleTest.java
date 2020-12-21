package org.jaybaws.adventofcode.y2020.day22;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.util.*;

public class Y2020D22PuzzleTest {

    public List<String> sample1 = Arrays.asList( );

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(-1, (long) new Y2020D22Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(-1, (long) new Y2020D22Puzzle().solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D22Puzzle(sample1);
        p.solution1();
        Assert.assertEquals(-1, p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D22Puzzle();
        p.solution1();
        Assert.assertEquals(-1, p.solution2());
    }

}