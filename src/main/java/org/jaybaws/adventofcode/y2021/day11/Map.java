package org.jaybaws.adventofcode.y2021.day11;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {

    private java.util.Map<Point, Octopus> map = new HashMap<Point, Octopus>();

    private static final int minX = 0;
    private static final int maxX = 9;
    private static final int minY = 0;
    private static final int maxY = 9;

    public Map(List<String> lines) {
       for (int y = minY; y <= maxY; y++)
           for (int x = minX; x <= maxX; x++) {
               int energy = Character.getNumericValue(lines.get(y).charAt(x));
               Octopus o = new Octopus(energy);
               this.map.put(new Point(x, y), o);
           }

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Point tp = new Point(x, y);
                Octopus o = this.map.get(tp);

                for (int x_offset = -1; x_offset <= 1; x_offset++)
                    for (int y_offset = -1; y_offset <= 1; y_offset++) {
                        Point np = new Point(x+x_offset, y+y_offset);
                        Octopus neighbour = this.map.get(np);
                        if (!np.equals(tp) && neighbour != null)
                            o.addNeighbour(neighbour);
                    }
            }
        }

        System.out.println("Before any steps:\n" + this);
    }

    public long play(int steps) {
        for (int step = 1; step <= steps; step++) {

            for (int y = minY; y <= maxY; y++) for (int x = minX; x <= maxX; x++) this.map.get(new Point(x, y)).charge();
            for (int y = minY; y <= maxY; y++) for (int x = minX; x <= maxX; x++) this.map.get(new Point(x, y)).tryFlash();
            for (int y = minY; y <= maxY; y++) for (int x = minX; x <= maxX; x++) this.map.get(new Point(x, y)).tryDischarge();

            if (new ArrayList<>(Arrays.asList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100})).contains(step))
                System.out.println("After step " + step + ":\n" + this);
        }

        long totalFlashes = 0;
        for (int y = minY; y <= maxY; y++)
            for (int x = minX; x <= maxX; x++)
                totalFlashes += this.map.get(new Point(x, y)).getTotalFlashes();

        return totalFlashes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                Octopus o = this.map.get(new Point(x, y));
                sb.append(o.toString());
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean isMegaFlash() {
        for (int y = minY; y <= maxY; y++) for (int x = minX; x <= maxX; x++) if (!this.map.get(new Point(x, y)).hasFlashed()) return false;
        return true;
    }

}
