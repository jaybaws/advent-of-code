package org.jaybaws.adventofcode.y2020.day9;

import junit.framework.Assert;
import org.jaybaws.adventofcode.Puzzle;
import org.jaybaws.adventofcode.y2020.day8.Program;
import org.jaybaws.adventofcode.y2020.day8.Y2020D8Puzzle;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

public class Y2020D9PuzzleTest {

    static final String[] sample1 = new String[] { "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4", "acc +6" };

    @Test
    public void part1_sample_validationTest() {
        Program sampleProg = new Program(Arrays.asList(sample1));
        sampleProg.run(BigInteger.ONE, false);
        Assert.assertEquals(new BigInteger("5"), sampleProg.readMemory(Program.ACCUMULATOR));
    }

    @Test
    public void part1_actual_validationTest() {
        Puzzle p = new Y2020D8Puzzle();
        Assert.assertEquals(new BigInteger("2025"), p.solution1());
    }


    static final String[] sample2 = new String[] { "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4", "acc +6" };

    @Test
    public void part2_sample_validationTest() {
        BigInteger val = null;

        String[][] programs = new String[sample2.length][sample2.length];

        for (int i = 0; i < sample2.length; i++) {
            String[] programClone = sample2.clone();
            if (programClone[i].startsWith("jmp")) {
                programClone[i] = programClone[i].replace("jmp", "nop");
            }
            programs[i] = programClone;

            Program sampleProg = new Program(Arrays.asList(programClone));
            try {
                sampleProg.run(BigInteger.ONE, true);
                val = (BigInteger) sampleProg.readMemory(Program.ACCUMULATOR);
                // System.out.println("FOUND IT!!!" + val);
            } catch (RuntimeException e) {

            }
        }

        Assert.assertEquals(new BigInteger("8"), val);
    }

    @Test
    public void part2_actual_validationTest() {
        Puzzle p = new Y2020D8Puzzle();
        Assert.assertEquals(new BigInteger("2001"), p.solution2());
    }

}