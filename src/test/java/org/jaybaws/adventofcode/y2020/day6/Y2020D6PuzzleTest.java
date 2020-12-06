package org.jaybaws.adventofcode.y2020.day6;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2020D6PuzzleTest {

    static final String[] testData = new String[] { "abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b" };

    @Test
    public void part1_sample_validationTest() {
        List<String> sampleData = Arrays.asList( testData );
        Puzzle tp = new Y2020D6Puzzle(sampleData);

        Assert.assertEquals(11, tp.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        List<String> sampleData = Arrays.asList( testData );
        Puzzle tp = new Y2020D6Puzzle(sampleData);

        Assert.assertEquals(6, tp.solution2());
    }


}