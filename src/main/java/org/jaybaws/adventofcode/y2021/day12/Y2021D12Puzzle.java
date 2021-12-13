package org.jaybaws.adventofcode.y2021.day12;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2021D12Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D12Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        return; // We good!
    }

    public Y2021D12Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        Graph g = new Graph(this.puzzleInput, 1);
        return g.walks();
    }

    @Override
    public Long solution2() {
        Graph g = new Graph(this.puzzleInput, 2);
        return g.walks();
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D12Puzzle();

        System.out.println("");
        System.out.println(String.format("\n\n\u001B[34mQuestion 1\u001B[0m?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\n\u001B[34mQuestion 2\u001B[0m?\n--> Well, this: %s.", puzzle.solution2()));
    }

}