package org.jaybaws.adventofcode.y2020.day12;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.List;

public class Y2020D12Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D12Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D12Puzzle() { super(); }

    @Override
    protected void prepare() {

    }

    @Override
    public Integer solution1() {
        Position shipPosition = new Position(0, 0, Direction.E);

        for (String s : puzzleInput) {
            Move.fromString(s).doMove(shipPosition);
        }

        return shipPosition.getManhattanDistance(0, 0);
    }

    @Override
    public Integer solution2() {
        Position ship = new Position(0, 0, Direction.E);
        Position waypoint = new Position(10, 1, null);

        for (String s : puzzleInput) {
            Move.fromString(s).doMove(ship, waypoint);
            if (TRACE)
                System.out.println(String.format("waypoint=%s ship=%s.", waypoint, ship));
        }

        return ship.getManhattanDistance(0, 0);

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D12Puzzle();

        System.out.println(String.format("[q1] ? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("[q2] ? Well, this: %d.", puzzle.solution2()));
    }

}