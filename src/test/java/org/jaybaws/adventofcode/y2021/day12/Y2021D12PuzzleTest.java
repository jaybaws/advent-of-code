package org.jaybaws.adventofcode.y2021.day12;
import junit.framework.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class Y2021D12PuzzleTest {

    public List<String> sample1a = Arrays.asList( new String[] {
            "start-A",
            "start-b",
            "A-c",
            "A-b",
            "b-d",
            "A-end",
            "b-end"
    } );

    public List<String> sample1b = Arrays.asList( new String[] {
            "dc-end",
            "HN-start",
            "start-kj",
            "dc-start",
            "dc-HN",
            "LN-dc",
            "HN-end",
            "kj-sa",
            "kj-HN",
            "kj-dc"

    } );

    public List<String> sample1c = Arrays.asList( new String[] {
            "fs-end",
            "he-DX",
            "fs-he",
            "start-DX",
            "pj-DX",
            "end-zg",
            "zg-sl",
            "zg-pj",
            "pj-he",
            "RW-he",
            "fs-DX",
            "pj-RW",
            "zg-RW",
            "start-pj",
            "he-WI",
            "zg-he",
            "pj-fs",
            "start-RW"
    } );

    @Test
    public void part1a_sample_validationTest() {
        Assert.assertEquals(10, (long) new Y2021D12Puzzle(sample1a).solution1());
    }

    @Test
    public void part1b_sample_validationTest() {
        Assert.assertEquals(19, (long) new Y2021D12Puzzle(sample1b).solution1());
    }

    @Test
    public void part1c_sample_validationTest() {
        Assert.assertEquals(226, (long) new Y2021D12Puzzle(sample1c).solution1());
    }

    @Test
    public void part2a_sample_validationTest() {
        Assert.assertEquals(36, (long) new Y2021D12Puzzle(sample1a).solution2());
    }

    @Test
    public void part2b_sample_validationTest() {
        Assert.assertEquals(103, (long) new Y2021D12Puzzle(sample1b).solution2());
    }

    @Test
    public void part2c_sample_validationTest() {
        Assert.assertEquals(3509, (long) new Y2021D12Puzzle(sample1c).solution2());
    }

}