package org.jaybaws.adventofcode.y2020.day3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Puzzle1and2 {

    static final boolean TRACE = false;

    static final int map_width = 32;
    static final int map_depth = 323;

    static char[][] map = new char[map_depth][map_width];

    static long howManyTrees(int h_step, int v_step) {
        int h = 0; // Starting 'horizontal' point is the left-most.
        int v = 0; // Starting 'vertical' point is the top-most.

        long treesEncountered = 0;

        while (v < (map_depth - 1)) {
            h = h + h_step;
            v = v + v_step;

            int normalized_h = h % (map_width - 1);
            char e = map[v][normalized_h];
            boolean isTree = (e == '#');

            if (TRACE)
                System.out.println(String.format("Encountered '%c' at [%d,(%d,%d)], which is a tree: %b.", e, h, normalized_h, v, isTree));

            if (isTree)
                treesEncountered++;
        }

        return treesEncountered;
    }

    public static void main(String[] args) {
        try (InputStreamReader streamReader =new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("input_2020_3.txt"), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                map[i] = chars;
                i++;
            }

            System.out.println(String.format("Encountered %d trees along the slope!", howManyTrees(3, 1)));

            long product =
                    howManyTrees(1, 1) * howManyTrees(3, 1) * howManyTrees(5, 1) * howManyTrees(7, 1) *howManyTrees(1, 2);

            System.out.println(String.format("What do you get if you multiply together the number of trees encountered on each of the listed slopes? Well, this: %d.", product));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}