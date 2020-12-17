package org.jaybaws.adventofcode.y2020.day17;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import javax.vecmath.Point4d;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Y2020D17Puzzle2 extends BasePuzzle {

    public static final boolean TRACE = false;

    static final char active = '#';

    Set<Point4d> activeSpace;
    double x_min;
    double x_max;
    double y_min;
    double y_max;
    double z_min;
    double z_max;
    double w_min;
    double w_max;

    public Y2020D17Puzzle2() {
        super();
    }

    public Y2020D17Puzzle2(List<String> altInput) {
        super(altInput);
    }

    public static Set<Point4d> surroundingPoints(Point4d p) {
        Set<Point4d> points = new HashSet<Point4d>();
        for (double x = p.x-1; x <= p.x+1; x++)
            for (double y = p.y-1; y <= p.y+1; y++)
                for (double z = p.z-1; z <= p.z+1; z++)
                    for (double w = p.w-1; w <= p.w+1; w++) {
                        Point4d newPoint = new Point4d(x, y, z, w);
                        if (!newPoint.equals(p))
                            points.add(newPoint);
                    }

        return points;
    }

    private void mark(double x, double y, double z, double w, boolean active, Set<Point4d> inSpace) {
        Point4d p = new Point4d(x,y,z,w);

        if (active) {
            inSpace.add(p);
        } else {
            inSpace.remove(p);
        }

        if (x < x_min) x_min = x;
        if (x > x_max) x_max = x;
        if (y < y_min) y_min = y;
        if (y > y_max) y_max = y;
        if (z < z_min) z_min = z;
        if (z > z_max) z_max = z;
        if (w < w_min) w_min = w;
        if (w > w_max) w_max = w;
    }

    @Override
    protected void prepare() {
        activeSpace = new HashSet<Point4d>(); x_min = 0; x_max = 0; y_min = 0; y_max = 0; z_min = 0; z_max = 0; w_min = 0; w_max = 0;

        int z = 0;
        int w = 0;
        for (int y = 0; y < puzzleInput.size(); y++) {
            String line = puzzleInput.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == active) {
                    mark(x, y, z, w, true, activeSpace);
                }
            }
        }
    }

    public boolean isActive(double x, double y, double z, double w, Set<Point4d> space) {
        return space.contains(new Point4d(x,y,z,w));
    }

    @Override
    public Integer solution2() {
        if (TRACE)
            System.out.println(String.format("Before: %d active cubes in %sx%sx%s%s space.", activeSpace.size(), (int) x_max-x_min, (int) y_max-y_min, (int) z_max-z_min, (int) w_max-w_min));
        for (int cycle = 1; cycle <= 6; cycle++) {
            Set<Point4d> newActiveSpace = new HashSet<Point4d>(activeSpace);
            for (double x = x_min-1; x <= x_max+1; x++)
                for (double y = y_min-1; y <= y_max+1; y++)
                    for (double z = z_min-1; z <= z_max+1; z++)
                        for (double w = w_min-1; w <= w_max+1; w++) {
                            Point4d thisPoint = new Point4d(x,y,z,w);
                            Set<Point4d> activeNeighbors = surroundingPoints(thisPoint);
                            activeNeighbors.retainAll(activeSpace);
                            int activeNeigborCount = activeNeighbors.size();

                            if (isActive(x,y,z, w, activeSpace)) {
                                if (!(activeNeigborCount == 2 || activeNeigborCount == 3)) {
                                    mark(x,y,z, w,false,newActiveSpace);
                                }
                            } else {
                                if (activeNeigborCount == 3) {
                                    mark(x,y,z, w,true, newActiveSpace);
                                }
                            }
                    }
            activeSpace = newActiveSpace;
            if (TRACE)
                System.out.println(String.format("After %d cycle: %d active cubes in %sx%sx%s%s space.", cycle, activeSpace.size(), (int) x_max-x_min, (int) y_max-y_min, (int) z_max-z_min, (int) w_max-w_min));
        }

        return activeSpace.size();
    }

    @Override
    public Integer solution1() {
        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D17Puzzle2();
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));
    }

}