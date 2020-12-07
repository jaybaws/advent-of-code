package org.jaybaws.adventofcode.y2020.day7;
import junit.framework.Assert;
import org.junit.Test;
import org.jaybaws.adventofcode.*;
import java.util.Arrays;

public class Y2020D7PuzzleTest {

    private String[] sample1 = new String[] {
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
    };

    @Test
    public void part1_sample_validationTest() {
        Puzzle puzzle = new Y2020D7Puzzle(Arrays.asList(sample1));
        Assert.assertEquals(4, puzzle.solution1());
    }

    @Test
    public void part1_atual_validationTest() {
        Puzzle puzzle = new Y2020D7Puzzle();
        Assert.assertEquals(272, puzzle.solution1());
    }

    private String[] sample2 = new String[] {
            "shiny gold bags contain 2 dark red bags.",
            "dark red bags contain 2 dark orange bags.",
            "dark orange bags contain 2 dark yellow bags.",
            "dark yellow bags contain 2 dark green bags.",
            "dark green bags contain 2 dark blue bags.",
            "dark blue bags contain 2 dark violet bags.",
            "dark violet bags contain no other bags."
    };

    @Test
    public void part2a_sample_validationTest() {
        Puzzle puzzle = new Y2020D7Puzzle(Arrays.asList(sample1));
        Assert.assertEquals(32, puzzle.solution2());
    }

    @Test
    public void part2b_sample_validationTest() {
        Puzzle puzzle = new Y2020D7Puzzle(Arrays.asList(sample2));
        Assert.assertEquals(126, puzzle.solution2());
    }

    @Test
    public void part2_atual_validationTest() {
        Puzzle puzzle = new Y2020D7Puzzle();
        Assert.assertEquals(172246, puzzle.solution2());
    }
}