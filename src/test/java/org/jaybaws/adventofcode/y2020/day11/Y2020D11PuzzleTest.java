package org.jaybaws.adventofcode.y2020.day11;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2020D11PuzzleTest {

    private static final List<String> sample = Arrays.asList(new String[] {"L.LL.LL.LL",  "LLLLLLL.LL",  "L.L.L..L..",  "LLLL.LL.LL",  "L.LL.LL.LL",  "L.LLLLL.LL",  "..L.L.....",  "LLLLLLLLLL",  "L.LLLLLL.L",  "L.LLLLL.LL" });

    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2020D11Puzzle(sample);
        Assert.assertEquals(37, p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D11Puzzle();
        Assert.assertEquals(2329, p.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D11Puzzle(sample);
        Assert.assertEquals(26, p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D11Puzzle();
        Assert.assertEquals(2138, p.solution2());

    }

}