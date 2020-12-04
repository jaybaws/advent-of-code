package org.jaybaws.adventofcode.y2019.day3;
import java.util.HashMap;
import java.util.Map;

public class Wire {

    static final boolean TRACE = false;

    private String name;
    private StringBuilder normalizedPath = new StringBuilder();

    private boolean trackTrail = false;

    private int x = 0; // Start at the central port
    private int y = 0; // Start at the central port

    private int step = 0;
    private int shortStep = 0;

    private Map<String, Integer> trail = new HashMap<String, Integer>(); // A trace of all the coordinates that have been touched.

    public Wire(String n, String path, boolean t) {
        trackTrail = t;
        name = n;
        String[] moves = path.split(",");
        for (String move : moves) {
            String direction = move.substring(0, 1);
            int distance = Integer.valueOf(move.substring(1));
            for (int i = 0; i < distance; i++) {
                normalizedPath.append(direction);
            }
        }

        // if (trackTrail) trail.put("0,0", step); // (control port doesn't count?)
    }

    public int getTotalStepsTaken() {
        return step;
    }

    public int getEffectiveStepsTaken() {
        return shortStep;
    }

    public boolean isDone() {
        return step == normalizedPath.length();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int stepsToCoordinate(String coordinate) {
        return trail.get(coordinate);
    }

    public String getName() {
        return name;
    }

    public void advance() {
        step++;
        String action = normalizedPath.substring(step - 1, step);

        switch (action) {
            case "U":
                y++; break;
            case "D":
                y--; break;
            case "L":
                x--; break;
            case "R":
                x++; break;
        }

        if (trackTrail) {
            String currentCoordinate = String.format("%d,%d", x, y);
            if (trail.containsKey(currentCoordinate)) {
                shortStep = trail.get(currentCoordinate);
                if (TRACE)
                    System.out.println(String.format("%s intersected self at pos(%d); resetting to effective_pos(%d).", name, step, shortStep));
            } else {
                shortStep++;
                trail.put(currentCoordinate, shortStep);
            }
        }
    }

}