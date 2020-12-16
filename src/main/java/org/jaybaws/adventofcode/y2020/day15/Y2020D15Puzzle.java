package org.jaybaws.adventofcode.y2020.day15;
import org.jaybaws.adventofcode.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Y2020D15Puzzle extends BasePuzzle {

    public static final boolean TRACE = true;

    public Y2020D15Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D15Puzzle() { super(); }

    private List<Integer> turns;

    private void speak(Integer t, Integer i) {
        turns.add(i);
        if (TRACE)
            if (t % 10000 == 0)
                System.out.print(String.format("%d = %d    \r", t, i));
    }

    public long countSpoken(Integer i) {
        return turns.stream().filter(x -> x.equals(i)).count();
    }

    private Integer lastNumberSpoken() {
        return turns.get(turns.size() - 1);
    }

    @Override
    protected void prepare() {
        turns = new ArrayList<Integer>();
        int t = 0;
        for (String s : puzzleInput)
            speak(++t, Integer.valueOf(s));
    }


    @Override
    public Integer solution1() {
        Integer lns = null;
        for (int t = puzzleInput.size() + 1; t <= 2020; t++) {
            lns = lastNumberSpoken();
            if (countSpoken(lns) <= 1) {
                speak(t,0);
            } else {
                int lastSpoken = turns.lastIndexOf(lns);
                int oneButLastSpoken = turns.subList(0, lastSpoken).lastIndexOf(lns);
                int age = lastSpoken - oneButLastSpoken;
                speak(t, age);
            }
        }

        return lastNumberSpoken();
    }


    private static Long calculate(List<Long> numbers, long target) {
        final HashMap<Long, Long> map = new HashMap<>();
        long i = 0;
        for (; i < numbers.size() - 1; i++) {
            map.put(numbers.get((int) i), (long) i);
        }

        long n = numbers.get((int) i);
        for (; i < target - 1; i++) {
            final Long prev = map.get(n);
            map.put(n, i);
            if (prev == null) {
                n = 0;
            } else {
                n = i - prev;
            }

        }

        return n;
    }

    @Override
    public Long solution2() {
        List<Long> numbers = turns.stream().mapToLong(Integer::longValue).boxed().collect(Collectors.toList());
        return calculate(numbers, 30000000);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D15Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));
    }

}