package org.jaybaws.adventofcode.y2020.day23;
import junit.framework.Assert;
import org.junit.Test;
import java.math.BigInteger;
import java.util.*;

public class Y2020D23PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] { "389125467" } );

    @Test
    public void part1a_sample_validationTest() { Assert.assertEquals("92658374", new Y2020D23Puzzle(sample1).solution1(10)); }

    @Test
    public void part1b_sample_validationTest() { Assert.assertEquals("67384529", new Y2020D23Puzzle(sample1).solution1(100)); }

    @Test
    public void part1_actual_validationTest() { Assert.assertEquals("68245739", new Y2020D23Puzzle().solution1()); }

    @Test
    public void part2_sample_validationTest() { Assert.assertEquals(new BigInteger("149245887792"), new Y2020D23Puzzle(sample1).solution2()); }

    @Test
    public void part2_actual_validationTest() { Assert.assertEquals(new BigInteger("219634632000"), new Y2020D23Puzzle().solution2()); }

}