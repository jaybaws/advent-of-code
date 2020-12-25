package org.jaybaws.adventofcode.y2020.day24;
import org.jaybaws.adventofcode.*;
import javax.vecmath.Point3d;
import java.util.*;

public class Y2020D24Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D24Puzzle(List<String> altInput) {
        super(altInput);
    }

    Set<Point3d> blackTiles;

    @Override
    protected void prepare() {
        blackTiles = new HashSet<Point3d>();

        for (String tile : puzzleInput) {
            List<String> moves = new ArrayList<String>();

            // [sauce: https://www.redblobgames.com/grids/hexagons/ ]
            int x = 0; int y = 0; int z = 0;
            while (tile.length() > 0) {
                String m = tile.substring(0, 1);
                if (m.equals("s") || m.equals("n"))
                    m = tile.substring(0, 2);
                moves.add(m);
                tile = tile.substring(m.length()); // Cut our current instruction off

                switch (m) {
                    case "e":
                        x++; y--; break;
                    case "se":
                        y--; z++; break;
                    case "sw":
                        x--; z++; break;
                    case "w":
                        x--; y++; break;
                    case "nw":
                        y++; z--; break;
                    case "ne":
                        x++; z--; break;
                }

            }

            Point3d p = new Point3d(x, y, z);
            if (blackTiles.contains(p)) blackTiles.remove(p); else blackTiles.add(p);
        }
    }

    public Y2020D24Puzzle() {
        super();
    }

    @Override
    public Object solution1() {
        return blackTiles.size();
    }


    private Set<Point3d> surroundingTiles(double x, double y, double z) { return surroundingTiles((int) x, (int) y, (int) z); }
    private Set<Point3d> surroundingTiles(int x, int y, int z) {
        return new HashSet(Arrays.asList(new Point3d[]{
                new Point3d(x+1, y-1, z),
                new Point3d(x, y-1, z+1),
                new Point3d(x-1, y, z+1),
                new Point3d(x-1,y+1, z),
                new Point3d(x, y+1, z-1),
                new Point3d(x+1, y, z-1)
        } ));
    }

    private int surroundingBlackTiles(double x, double y, double z) { return surroundingBlackTiles((int) x, (int) y, (int) z); }
    private int surroundingBlackTiles(int x, int y, int z) { // @TODO: do set intersect -> count?
        int count = 0;
        for (Point3d st : surroundingTiles(x, y, z))
            if (blackTiles.contains(st))
                count++;

        return count;
    }

    public Set<Point3d> whiteTiles() {
        Set<Point3d> result = new HashSet<Point3d>();
        for (Point3d b : blackTiles)
            for (Point3d st : surroundingTiles(b.x, b.y, b.z))
                if (!blackTiles.contains(st))
                    result.add(st);

        return result;
    }

    public Object solution2(int days) {
        if (TRACE)
            System.out.println(String.format("Start: %d", blackTiles.size()));
//        System.out.println(String.format("--> black tiles: %s", blackTiles));

        for (int day = 1; day <= days; day++) {
            Set<Point3d> target = new HashSet<Point3d>(blackTiles); // Make a copy of the floor

            for (Point3d blackTile : blackTiles) {
                int adjacentBlackTileCount = surroundingBlackTiles(blackTile.x, blackTile.y, blackTile.z);
                if (adjacentBlackTileCount == 0 || adjacentBlackTileCount > 2)
                    target.remove(blackTile); // Flip to white
            }

            for (Point3d whiteTile : whiteTiles()) {
                int adjacentBlackTileCount = surroundingBlackTiles(whiteTile.x, whiteTile.y, whiteTile.z);
                if (adjacentBlackTileCount == 2)
                    target.add(whiteTile); // Flip to black
            }

            blackTiles = target;
            if (TRACE)
                System.out.println(String.format("Day %d: %d", day, blackTiles.size()));
//            System.out.println(String.format("--> black tiles: %s", blackTiles));
        }

        return blackTiles.size();
    }

    @Override
    public Object solution2() {
        return solution2(100);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D24Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}