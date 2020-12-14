package org.jaybaws.adventofcode.y2020.day14;
import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Arrays;

public class Y2020D14PuzzleTest {

    static final String[] sample1 = new String[] {
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
    };

    static final String[] sample2 = new String[] {
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
    };

    @Test
    public void conversionTest() {
        Assert.assertEquals(new BigInteger("3415608991"), BitMask.apply("000X110X1X111XX1011000001110100111X1", new BigInteger("949958")));
    }

    @Test
    public void part1_sample_validationTest() {
        Puzzle p = new Y2020D14Puzzle(Arrays.asList(sample1));
        Assert.assertEquals(new BigInteger("165"), p.solution1());
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D14Puzzle();
        Assert.assertEquals(new BigInteger("11501064782628"), p.solution1());
    }

    @Test
    public void part2_sample_validationTest() {
        Puzzle p = new Y2020D14Puzzle(Arrays.asList(sample2));
        Assert.assertEquals(new BigInteger("208"), p.solution2());
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D14Puzzle();
        Assert.assertEquals(new BigInteger("5142195937660"), p.solution2());
    }

}