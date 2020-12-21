package org.jaybaws.adventofcode.y2020.day20;
import javax.vecmath.Point2d;
import java.util.*;

public class Image {

    private Map<String, Tile> tiles = new HashMap<String, Tile>();
    private final int size;
    private Set<String> edges;

    public Image(int size, Set<String> edges) {
        this.size = size;
        this.edges = edges;
    }

    public static String toKey(Point2d point) {
        return String.format("(%s,%s)", point.x, point.y);
    }

    public boolean place(int x, int y, Tile t) {
        String key = toKey(new Point2d(x, y));
        if (x >= size || y >= size || tiles.containsKey(key)) {
            return false; // out of bounds, or tile already placed at this position!
        } else {
            for (int flips = 1; flips <= 2; flips++) {
                for (int rotations = 1; rotations <= 4; rotations++) {
                    List<Boolean> checks = new ArrayList<Boolean>();

                    if (x == 0) {
                        // left must be edge
                        checks.add(edges.contains(t.leftEdge()));
                    } else if (x == size - 1) {
                        // right must be edge
                        checks.add(edges.contains(t.rightEdge()));
                    }
                    if (x > 0) {
                        // left edge must match x-1's right edge!
                        Tile o = tiles.get(toKey(new Point2d(x - 1, y)));
                        checks.add(t.leftEdge().equals(o.rightEdge()));
                    }

                    if (y == 0) {
                        // top must be edge
                        checks.add(edges.contains(t.topEdge()));
                    } else if (y == size - 1) {
                        // bottom must be edge
                        checks.add(edges.contains(t.bottomEdge()));
                    }
                    if (y > 0) {
                        // top must match y-1's bottom
                        Tile o = tiles.get(toKey(new Point2d(x,y - 1)));
                        checks.add(t.topEdge().equals(o.bottomEdge()));
                    }

                    if (!checks.contains(false)) {
                        tiles.put(key, t); // Store it, at the requested place in the image, in it's current rotation/flip
                        return true;
                    }

                    t.rotate(1); // We still here --> Rotatorizify!
                }

                t.flip(); // flip-mode!
            }

            return false; // Nope, guess not...
        }
    }

    public char[][] getImage() {
        int depth = tiles.get(toKey(new Point2d(0,0))).height();

        char[][] image = new char[size * depth][size * depth];
        int row = 0;
        int col = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile t = tiles.get(toKey(new Point2d(i,j)));
                char[][] pixels = t.getPixels();
                row = i * 8;
                col = j * 8;
                for (int r = 1; r <= pixels.length - 2; r++) {
                    for (int c = 1; c <= pixels[0].length - 2; c++) {
                        image[row][col] = pixels[r][c];
                        col++;
                    }
                    row++;
                    col = j * 8;
                }
            }
        }

        return image;
    }

}