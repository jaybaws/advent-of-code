package org.jaybaws.adventofcode.y2020.day16;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;

import java.util.*;

public class Y2020D16Puzzle extends BasePuzzle {

    public static final boolean TRACE = true;

    public Y2020D16Puzzle(List<String> altInput) {
        super(altInput);
    }

    private Map<String, List<Range>> rules;
    private String myTicket;
    private List<String> nearbyTickets;
    private Set<String> validNearbyTickets;
    private List<String> meta;

    @Override
    protected void prepare() {
        validNearbyTickets = new HashSet<String>();
        myTicket = puzzleInput.get(puzzleInput.indexOf("your ticket:") + 1);
        nearbyTickets = puzzleInput.subList(puzzleInput.indexOf("nearby tickets:") + 1, puzzleInput.size());
        meta = puzzleInput.subList(0, puzzleInput.indexOf("your ticket:") - 1);

        rules = new HashMap<String, List<Range>>();
        for (String line : meta) {
            String name = StringUtils.substringBefore(line, ": ");
            String constraints[] = StringUtils.substringAfter(line, ": ").split(" or ");
            List<Range> ranges = new ArrayList<Range>();

            for (String c : constraints) {
                ranges.add(Range.between(Integer.valueOf(StringUtils.substringBefore(c, "-")), Integer.valueOf(StringUtils.substringAfter(c, "-"))));
            }

            rules.put(name, ranges);
        }
    }

    public Y2020D16Puzzle() {
        super();
    }

    @Override
    public Integer solution1() {
        Integer result = 0;

        for (String t : nearbyTickets) {
            String[] vals = t.split(",");

            int count = 0;
            for (String val : vals) {
                boolean inSomeRange = false;
                int v = Integer.valueOf(val);

                for (List<Range> c : rules.values())
                    for (Range r : c) {
                        if (r.contains(v))
                            inSomeRange = true;
                    }

                if (!inSomeRange) {
                    result += v;
                } else {
                    count++;
                }
            }

            if (count == t.split(",").length) {
                validNearbyTickets.add(t);
            }
        }

        return result;
    }

    @Override
    public Long solution2() {
        int[][] columns = new int[20][validNearbyTickets.size()];

        // Make a flipped matrix (90 degrees clockwise rotation of all valid tickets)
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < validNearbyTickets.size(); j++) {
                columns[i][j] = Integer.valueOf(validNearbyTickets.toArray(new String[0])[j].split(",")[i]);
            }
        }

        Map<Integer, List<String>> possibilities = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            int[] vals = columns[i];

            for (String ruleName : rules.keySet()) {
                List<Range> constraints = rules.get(ruleName);
                boolean match = true;
                for (int v : vals) {
                    if (!(constraints.get(0).contains(v) || constraints.get(1).contains(v))) {
                        match = false;
                    }
                }
                if (match) {
                    if (possibilities.containsKey(i)) {
                        List<String> x = new ArrayList<>( possibilities.get(i) );
                        x.add(ruleName);
                        possibilities.replace(i, x);
                    } else {
                        possibilities.put(i, Arrays.asList(new String[] { ruleName }));
                    }
                }

            }
        }

        List <String> solution = new ArrayList<String>();
        boolean isDone = false;
        while (!isDone) {
            boolean didSomething = false;
            for (Integer key : possibilities.keySet()) {
                List<String> types = possibilities.get(key);

                if (types.size() == 1) {
                    String type = types.get(0);
                    solution.add(key + "," + type);

                    for (Integer kx : possibilities.keySet()) {
                        List<String> tx = new ArrayList<String>(possibilities.get(kx));
                        tx.remove(type);
                        possibilities.replace(kx, tx);
                    }
                    didSomething = true;
                }
            }
            if (!didSomething)
                isDone = true;
        }

        // System.out.println(solution);

        long answer = 1;
        for (String s : solution) {
            if (s.contains(",departure")) {
                int i = Integer.valueOf(StringUtils.substringBefore(s, ","));
                long val = Long.valueOf(myTicket.split(",")[i]);
                // System.out.println(i + " = " + val);
                answer *= val;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D16Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));
    }

}