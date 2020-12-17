package org.jaybaws.adventofcode.y2020.day17;
import org.jaybaws.adventofcode.*;
import javax.vecmath.Point3d;
import java.util.*;
import java.util.List;

public class Y2020D17Puzzle1 extends BasePuzzle {

    public static final boolean TRACE = false;

    static final char active = '#';
    static final char inActive = '.';

    Set<Point3d> activeSpace;
    double x_min;
    double x_max;
    double y_min;
    double y_max;
    double z_min;
    double z_max;

    public Y2020D17Puzzle1() {
        super();
    }

    public Y2020D17Puzzle1(List<String> altInput) {
        super(altInput);
    }

    public static Set<Point3d> surroundingPoints(Point3d p) {
        Set<Point3d> points = new HashSet<Point3d>();
        for (double x = p.x-1; x <= p.x+1; x++)
            for (double y = p.y-1; y <= p.y+1; y++)
                for (double z = p.z-1; z <= p.z+1; z++) {
                    Point3d newPoint = new Point3d(x, y, z);
                    if (!newPoint.equals(p))
                        points.add(newPoint);
                }

        return points;
    }

    private void mark(double x, double y, double z, boolean active, Set<Point3d> inSpace) {
        Point3d p = new Point3d(x,y,z);

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
    }

    @Override
    protected void prepare() {
        activeSpace = new HashSet<Point3d>(); x_min = 0; x_max = 0; y_min = 0; y_max = 0; z_min = 0; z_max = 0;

        int z = 0;
        for (int y = 0; y < puzzleInput.size(); y++) {
            String line = puzzleInput.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == active) {
                    mark(x, y, z, true, activeSpace);
                }
            }
        }
    }

    public boolean isActive(double x, double y, double z, Set<Point3d> space) {
        return space.contains(new Point3d(x,y,z));
    }

    @Override
    public Integer solution1() {
        if (TRACE)
            System.out.println(String.format("Before: %d active cubes in %sx%sx%s space.", activeSpace.size(), (int) x_max-x_min, (int) y_max-y_min, (int) z_max-z_min));
        for (int cycle = 1; cycle <= 6; cycle++) {
            Set<Point3d> newActiveSpace = new HashSet<Point3d>(activeSpace);
            for (double x = x_min -1; x <= x_max+1; x++)
                for (double y = y_min - 1; y <= y_max+1; y++)
                    for (double z = z_min -1; z <= z_max+1; z++) {
                        Point3d thisPoint = new Point3d(x,y,z);
                        Set<Point3d> activeNeighbors = surroundingPoints(thisPoint);
                        activeNeighbors.retainAll(activeSpace);
                        int activeNeigborCount = activeNeighbors.size();

                        if (isActive(x,y,z, activeSpace)) {
                            if (!(activeNeigborCount == 2 || activeNeigborCount == 3)) {
                                mark(x,y,z,false,newActiveSpace);
                            }
                        } else {
                            if (activeNeigborCount == 3) {
                                mark(x,y,z, true, newActiveSpace);
                            }
                        }
                    }
            activeSpace = newActiveSpace;
            if (TRACE)
                System.out.println(String.format("After %d cycle: %d active cubes in %sx%sx%s space.", cycle, activeSpace.size(), (int) x_max-x_min, (int) y_max-y_min, (int) z_max-z_min));
        }

        return activeSpace.size();
    }

    @Override
    public Integer solution2() {
        prepare(); // RELOAD!

        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D17Puzzle1();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        // System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));
    }

}