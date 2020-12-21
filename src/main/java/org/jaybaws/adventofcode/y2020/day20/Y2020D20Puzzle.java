package org.jaybaws.adventofcode.y2020.day20;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2020D20Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D20Puzzle(List<String> altInput) {
        super(altInput);
    }

    private List<Tile> tiles;
    private List<Tile> cornerTiles;
    private Set<String> edges;

    @Override
    protected void prepare() {
        this.tiles = new ArrayList<Tile>();

        String[] tileArray = String.join("\n", puzzleInput).split("\n\n");
        for (String tileStr : tileArray) {
            Tile t = new Tile(tileStr.split("\n"));
            tiles.add(t);
        }
    }

    public Y2020D20Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        this.cornerTiles = new ArrayList<Tile>();
        this.edges = new HashSet<String>();

        Long product = 1l;
        for (Tile t : tiles) {
            List<String> allOtherSides = new ArrayList<String>();
            for (Tile o : tiles) {
                if (o.getId() != t.getId()) {
                    allOtherSides.addAll(o.getPossibleSides());
                }
            }

            int mismatches = 0;
            for (String side : t.getPossibleSides()) {
                if (!allOtherSides.contains(side)) {
                    mismatches++;
                    edges.add(side);
                }
            }

            if (mismatches == 2*2) {
                product *= t.getId();
                cornerTiles.add(t);
            }
        }

        if (TRACE)
            System.out.println(String.format("Identified the following tiles as corners: %s.", cornerTiles));

        return product;
    }

    @Override
    public Long solution2() {
        int size = (int) Math.sqrt(tiles.size());
        Image image = new Image(size, edges);

        for (int h = 0; h < size; h++) {
            for (int w = 0; w < size; w++) {
                // if (w == 0 && h == 0) continue;
                boolean placed = false;

                tileloop:
                for (int i = 0; !placed && i < tiles.size(); i++) {
                    Tile t = tiles.get(i);
                    placed = image.place(w, h, t);
                    if (placed) {
                        tiles.remove(t);
                        break tileloop;
                    }
                }

            }
        }

        Tile finalImage = new Tile(null, image.getImage());
        for (int flips = 1; flips <= 2; flips++) {
            for (int rotations = 1; rotations <= 4; rotations++) {
                char[][] markedImage = Calypso.mark(finalImage.getPixels());
                finalImage = new Tile(null, markedImage);

                if (TRACE) {
                    finalImage.print();
                    System.out.println("\n\n\n");
                }

                finalImage.rotate(1);
            }

            finalImage.flip();
        }

        int count = 0;
        char[][] pixels = finalImage.getPixels();
        for (int row = 0; row < pixels.length; row++)
            for (int col = 0; col < pixels[0].length; col++)
                if (pixels[row][col] == '#')
                    count++;

        return (long) count;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D20Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        // total pixel-count in 'my' image is 2506. That means the solution = 2506 - (n * 15), where n is the amount of monsters in the image.
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2())); // 2121 -> 23 monsters! Yikes!!!

    }

}