package org.jaybaws.adventofcode.y2021.day13;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Paper {

    private Set<Point> dots = new HashSet<Point>();
    private int minX = 0;
    private int maxX = 0;
    private int minY = 0;
    private int maxY = 0;

    public void dot(int x, int y) {
        Point p = new Point(x, y);
        if (!dots.contains(p)) {
            // System.out.printf("Dotting (%d, %d)%n",x,y);
            dots.add(p);
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        } else {
            System.out.printf("Already dotted!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                sb.append(dots.contains(new Point(x,y))? "#":".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public long visibleDots() {
        return dots.size();
    }

    public void execute(String instruction) {
        char axis = instruction.charAt(11);
        int place = Integer.valueOf(instruction.split("=")[1]);

        switch (axis) {
            case 'x':
                foldVertical(place);
                break;
            case 'y':
                foldHorizontal(place);
                break;
            default:
                break;
        }
    }

    private void foldVertical(int f) {
        // System.out.printf("Vertical fold at %d.%n", f);

        Set<Point> newDots = new HashSet<Point>();
        for (int y = minY; y <= maxY; y++)
            for (int x = minX; x <= maxX; x++) {
                if (x < f) {
                    if (dots.contains(new Point(x, y)))
                        newDots.add(new Point(x, y));
                } else if (x == f) {
                    // Skip that line!
                } else {
                    // System.out.println("Hey!");
                    if (dots.contains(new Point(x, y)))
                        newDots.add(new Point(f - (x - f),y));
                }
            }
        dots = newDots;

        maxX = f - 1;
        if (f < (maxX / 2)) minX -= (maxX / 2) - f;

    }

    private void foldHorizontal(int f) {
        // System.out.printf("Horizontal fold at %d.%n", f);

        Set<Point> newDots = new HashSet<Point>();
        for (int y = minY; y <= maxY; y++)
            for (int x = minX; x <= maxX; x++) {
                if (y < f) {
                    if (dots.contains(new Point(x, y)))
                        newDots.add(new Point(x, y));
                } else if (y == f) {
                    // Skip that line!
                } else {
                        // System.out.println("Hey!");
                        if (dots.contains(new Point(x, y)))
                            newDots.add(new Point(x,f - (y - f)));
                }
            }
        dots = newDots;

        maxY = f - 1;
        if (f < (maxY / 2)) minY -= (maxY / 2) - f;

    }

}