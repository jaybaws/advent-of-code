package org.jaybaws.adventofcode.y2019.day6;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2019D6PuzzleTest {

    @Test
    public void part1_sample_validationTest() {
        List<String> sampleData = Arrays.asList( new String[] { "COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L" } );
        Puzzle tp = new Y2019D6Puzzle(sampleData);

        Assert.assertEquals(42, tp.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        List<String> sampleData = Arrays.asList( new String[] { "COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN" } );
        Puzzle tp = new Y2019D6Puzzle(sampleData);

        Assert.assertEquals(4, tp.solution2());

    }
}
