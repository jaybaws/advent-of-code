package org.jaybaws.adventofcode.y2021.day9;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.*;
import java.util.stream.Collectors;

public class Y2021D9Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D9Puzzle(List<String> altInput) {
        super(altInput);
    }

    private int maxX;
    private int maxY;
    private int[][] map;
    private Set<Coordinate> lowPoints;

    @Override
    protected void prepare() {
        lowPoints = new HashSet<Coordinate>();
        maxX = this.puzzleInput.get(0).length() - 1;
        maxY = this.puzzleInput.size() - 1;
        map = new int[maxX + 1][maxY + 1];

        for (int y = 0; y <= maxY; y++) {
            String line = this.puzzleInput.get(y);
            for (int x = 0; x <= maxX; x++) {
                int score = Integer.valueOf(Character.getNumericValue(line.charAt(x)));
                map[x][y] = score;
            }
        }
    }

    public Y2021D9Puzzle() {
        super();
    }

    private Integer getValue(int x, int y) {
        try {
            return map[x][y];
        } catch (Throwable t) {
            return null;
        }
    }

    private List<Integer> getAdjacentValues(int x, int y) {
        List<Integer> result = new ArrayList<Integer>();


        result.add(getValue(x+1, y));
        result.add(getValue(x-1, y));
        result.add(getValue(x, y+1));
        result.add(getValue(x, y-1));

        return result.stream().filter( v -> v != null).collect(Collectors.toList());
    }

    private List<Coordinate> getAdjacentCoordinates(int x, int y) {
        List<Coordinate> result = new ArrayList<Coordinate>();

        if (x == maxX) {
            result.add(new Coordinate(x - 1, y));
        } else if (x == 0) {
            result.add(new Coordinate(x + 1, y));
        } else {
            result.add(new Coordinate(x - 1, y));
            result.add(new Coordinate(x + 1, y));
        }

        if (y == maxY) {
            result.add(new Coordinate(x, y - 1));
        } else if (y == 0) {
            result.add(new Coordinate(x, y + 1));
        } else {
            result.add(new Coordinate(x, y - 1));
            result.add(new Coordinate(x, y + 1));
        }

        return result;
    }

    @Override
    public Long solution1() {
        long totalRiskScore = 0;

        for (int y = 0; y <= maxY; y++)
            for (int x = 0; x <= maxX; x++) {
                int currentRisk = getValue(x, y);
                List<Integer> adjacentValues = getAdjacentValues(x, y);
                int minAdjacentRisk = Collections.min(adjacentValues);

                if (currentRisk < minAdjacentRisk) {
                    totalRiskScore += 1 + currentRisk;
                    lowPoints.add(new Coordinate(x,y));
                }
            }

        return totalRiskScore;
    }

    private void workBasin(Coordinate c, Set<String> trace) {
        Integer thisValue = getValue(c.x, c.y);

        trace.add(c.toString());

        for (Coordinate ac : getAdjacentCoordinates(c.x, c.y)) {
            Integer adjacentValue = getValue(ac.x, ac.y);

            // In thise case, it just needs to 'flow down', not necessarily by 1 step!
            if (thisValue.longValue() < adjacentValue.longValue() && adjacentValue != 9) {
                String str = ac.toString();
                trace.add(str);
                workBasin(ac, trace);
            }
        }
    }

    @Override
    public Long solution2() {
        if (lowPoints.size() == 0)
            solution1(); // Force loading the lowPoints!

        System.out.println("low points: " + lowPoints);

        List<Integer> sizes = new ArrayList<Integer>();

        for (Coordinate c : lowPoints) {

            Set<String> trace = new HashSet<String>();

            workBasin(c, trace);

            System.out.println(trace.size() + " -->  " + trace);
            sizes.add(trace.size());
        }

        sizes.sort(Collections.reverseOrder());

        return (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D9Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}