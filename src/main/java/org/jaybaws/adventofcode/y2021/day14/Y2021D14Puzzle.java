package org.jaybaws.adventofcode.y2021.day14;
import org.jaybaws.adventofcode.*;
import java.util.*;
import java.util.stream.Collectors;

public class Y2021D14Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D14Puzzle(List<String> altInput) {
        super(altInput);
    }

    private String polymerTemplate;
    private Map<String,String> rules;

    @Override
    protected void prepare() {
        polymerTemplate = this.puzzleInput.get(0);
        rules = new HashMap<String, String>();

        for (int i = 2; i < this.puzzleInput.size(); i++) {
            String[] ruleParts = this.puzzleInput.get(i).split(" -> ");
            String pair = ruleParts[0];
            String insertion = ruleParts[1];

            rules.put(pair, insertion);
        }
    }

    public Y2021D14Puzzle() {
        super();
    }

    private List<String> toPairs(String s) {
        List<String> pairs = new ArrayList<String>();

        for (int pos = 0; pos < s.length() - 1; pos++)
            pairs.add(s.substring(pos, pos + 2));

        return pairs;
    }

    private void increase(String id, Map<String, Long> registry) {
        if (registry.containsKey(id)) {
            long val = registry.get(id);
            registry.replace(id, val + 1);
        } else {
            registry.put(id, 1l);
        }
    }

    private Long solve(int steps) {
        Map<String, Long> counts  = new HashMap<String, Long>();

        // Initial load of pairs
        for (String pair : toPairs(polymerTemplate)) {
            increase(pair, counts);
        }

        // Initial load the letters
        Map<String, Long> letters = polymerTemplate.chars().boxed()
                .map( s-> Character.toString(s))
                .collect(
                        Collectors.toMap(x -> x, x -> 1l, Long::sum)
                );

        for (int step = 1; step <= steps; step++) {
            Map<String, Long> newCounts = new HashMap<String, Long>();
            for (Map.Entry<String, Long> e : counts.entrySet()) {
                String pair = e.getKey();
                String l = pair.substring(0, 1), r = pair.substring(1, 2);
                String m = rules.get(pair);
                Long c = e.getValue();

                newCounts.merge(l + m, c, Long::sum);
                newCounts.merge(m + r, c, Long::sum);
                letters.merge(m, c, Long::sum);

            }
            counts = newCounts;
        }

        return Collections.max(letters.values()) - Collections.min(letters.values());
    }

    @Override
    public Long solution1() {
        return solve(10);
    }

    @Override
    public Long solution2() {
        return solve(40);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D14Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}