package org.jaybaws.adventofcode.y2020.day20;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Tile {

    private Integer id;
    private int width;
    private int height;
    private char[][] image;

    public Tile(String[] lines) {
        this.id = Integer.parseInt(StringUtils.substringBetween(lines[0], "Tile ", ":"));

        this.width = lines[0].length();
        this.height = lines.length - 1;

        this.image = new char[width][height];
        for (int w = 0; w < width; w++)
            for (int h = 0; h < height; h++)
                this.image[w][h] = lines[h + 1].charAt(w);
    }

    public Tile(Integer id, char[][] image) {
        this.id = id;
        this.image = image;
        this.width = image.length;
        this.height = image[0].length;
    }

    public Integer getId() {
        return this.id;
    }

    public String topEdge() { String top = ""; for (int w = 0; w < width; w++) top += image[w][0]; return top; }
    public String rightEdge() { String right = ""; for (int h = 0; h < height; h++) right += image[width - 1][h]; return right; }
    public String bottomEdge() { String bottom = ""; for (int w = 0; w < width; w++) bottom += image[w][height - 1]; return bottom; }
    public String leftEdge() { String left = ""; for (int h = 0; h < height; h++) left += image[0][h]; return left; }

    public List<String> getPossibleSides() {
        List<String> result = new ArrayList<String>();

        String top = this.topEdge();
        String bottom = this.bottomEdge();
        String left = this.leftEdge();
        String right = this.rightEdge();

        String[] sides = new String[] { top, right, bottom, left };
        for (String s : sides) {
            result.add(s);
            result.add(StringUtils.reverse(s));
        }

        return result;
    }

    public void rotate(int i) {
        for (int j = 1; j <= i; j++) {
            char[][] copy = new char[width][height];
            for (int r = 0; r < copy.length; r++)
                for (int c = 0; c < copy[0].length; c++)
                    copy[r][c] = image[width - 1 - c][r];

            image = copy;
        }
    }

    public void flip() {
        char[][] copy = new char[width][height];
        for (int w = 0; w < width; w++)
            for (int h = 0; h < height; h++)
                copy[width - 1 - w][h] = image[w][h];
        image = copy;
    }

    public char[][] getPixels() {
        return image;
    }

    public String toString() {
        return String.format("Tile('%d')", id);
    }

    public int height() {
        return this.height - 2;
    }

    private void print(char[][] image) {
        for (char[] chars : image)
            System.out.println(String.copyValueOf(chars));
    }

    public void print() {
        print(image);
    }

}