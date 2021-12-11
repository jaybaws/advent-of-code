package org.jaybaws.adventofcode.y2021.day11;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2021D11Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D11Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!

    }

    public Y2021D11Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        Map map = new Map(this.puzzleInput);
        return map.play(100);
    }

    @Override
    public Long solution2() {
        Map map = new Map(this.puzzleInput);

        long steps = 0;
        while (!map.isMegaFlash()) {
            steps++;
            map.play(1);
        }

        return steps;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D11Puzzle();

        System.out.println("\u001B[34mBlaat\u001B[0m");
        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}