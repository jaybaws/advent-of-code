package org.jaybaws.adventofcode.y2021.day14;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D14PuzzleTest {

    public List<String> sample1 = Arrays.asList( new String[] {
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C",
    } );

    @Test
    public void part1_sample_validationTest() {
        Assert.assertEquals(1588, (long) new Y2021D14Puzzle(sample1).solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Assert.assertEquals(2188189693529l, (long) new Y2021D14Puzzle(sample1).solution2());
    }

}