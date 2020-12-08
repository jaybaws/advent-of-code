package org.jaybaws.adventofcode.y2020.day8;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Y2020D8Puzzle extends BasePuzzle {

    public Y2020D8Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D8Puzzle() { super(); }

    private Program computer;

    @Override
    protected void prepare() {
        computer = new Program(puzzleInput);
    }

    @Override
    public BigInteger solution1() {
        computer.run(BigInteger.ONE, false);
        return (BigInteger) computer.readMemory(Program.ACCUMULATOR);
    }

    @Override
    public BigInteger solution2() {
        BigInteger val = null;

        String[][] programs = new String[2 * puzzleInput.size()][puzzleInput.size()];

        // This sure could use some make-up...
        for (int i = 0; i < puzzleInput.size(); i++) {
            String[] jmpClone = puzzleInput.toArray(new String[0]).clone();
            String[] nopClone = puzzleInput.toArray(new String[0]).clone();

            if (jmpClone[i].startsWith("jmp")) {
                jmpClone[i] = jmpClone[i].replace("jmp", "nop");
            }
            if (nopClone[i].startsWith("nop")) {
                nopClone[i] = nopClone[i].replace("nop", "jmp");
            }

            programs[i] = jmpClone;
            programs[i + puzzleInput.size()] = nopClone;

            Program jmpTester = new Program(Arrays.asList(jmpClone));
            try {
                jmpTester.run(BigInteger.ONE, true);
                val = (BigInteger) jmpTester.readMemory(Program.ACCUMULATOR);
            } catch (RuntimeException e) {}

            Program nopTester = new Program(Arrays.asList(nopClone));
            try {
                nopTester.run(BigInteger.ONE, true);
                val = (BigInteger) nopTester.readMemory(Program.ACCUMULATOR);
            } catch (RuntimeException e) { }

        }

        return val; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D8Puzzle();

        System.out.println(String.format("Immediately before any instruction is executed a second time, what value is in the accumulator? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the value of the accumulator after the program terminates? Well, this: %d.", puzzle.solution2()));
    }

}