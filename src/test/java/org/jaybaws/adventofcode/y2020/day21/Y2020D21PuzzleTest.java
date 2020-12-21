package org.jaybaws.adventofcode.y2020.day21;
import junit.framework.Assert;
import org.jaybaws.adventofcode.*;
import org.junit.Test;
import java.util.*;

public class Y2020D21PuzzleTest {

    public List<String> sample1 = Arrays.asList(new String[] {
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
    });

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(5, (long) new Y2020D21Puzzle(sample1).solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Assert.assertEquals(2556, (long) new Y2020D21Puzzle().solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D21Puzzle(sample1);
        p.solution1();
        Assert.assertEquals("mxmxvkd,sqjhc,fvjkl", p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D21Puzzle();
        p.solution1();
        Assert.assertEquals("vcckp,hjz,nhvprqb,jhtfzk,mgkhhc,qbgbmc,bzcrknb,zmh", p.solution2());
    }

}