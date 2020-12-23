package org.jaybaws.adventofcode.y2020.day22;
import junit.framework.Assert;
import org.junit.Test;
import java.util.*;

public class Y2020D22PuzzleTest {

    public List<String> sample1 = Arrays.asList(
            new String[] {
                    "Player 1:",
                    "9",
                    "2",
                    "6",
                    "3",
                    "1",
                    "",
                    "Player 2:",
                    "5",
                    "8",
                    "4",
                    "7",
                    "10"
            }
    );

    @Test
    public void part1_sample_validationTest() { Assert.assertEquals(306, (long) new Y2020D22Puzzle(sample1).solution1()); }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(32162, (long) new Y2020D22Puzzle().solution1());
    }

    @Test
    public void part2_sample_validationTest() { Assert.assertEquals(291, (long) new Y2020D22Puzzle(sample1).solution2()); }

    @Test
    public void part2_actual_validationTest() { Assert.assertEquals(32534, (long) new Y2020D22Puzzle().solution2()); }

}