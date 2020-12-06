package org.jaybaws.adventofcode.y2020.day5;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.stream.Collectors;
import java.util.*;

public class Y2020D5Puzzle extends BasePuzzle {

    protected Set<Integer> ids;

    public Y2020D5Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D5Puzzle() { super(); }

    @Override
    protected void prepare() {
        ids = new HashSet<Integer>();

        int highestId = 0;

        for (String seat : puzzleInput) {
            Seat s = new Seat(seat);
            ids.add(s.getSeatId());

            if (s.getSeatId() > highestId)
                highestId = s.getSeatId();
        }
    }

    @Override
    public Integer solution1() {
        return ids.stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer solution2() {
        int availableSeatId = 0;
        List<Integer> sortedIds = ids.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < sortedIds.size() - 1; i++) {
            int current = sortedIds.get(i);
            int next = sortedIds.get(i + 1);
            if (next == current + 2) {
                availableSeatId = current + 1;
            }
        }

        return availableSeatId;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D5Puzzle();

        System.out.println(String.format("What is the highest seat ID on a boarding pass? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the ID of your seat? Well, this: %d.", puzzle.solution2()));
    }
}