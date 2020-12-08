package org.jaybaws.adventofcode.y2019.day8;
import org.apache.commons.lang3.StringUtils;

public class Layer {

    public char[][] data;
    private String layerData;
    private int width;
    private int height;

    public Layer(String d, int w, int h) {
        layerData = d;
        width = w;
        height = h;
        char[] chars = d.toCharArray();
        this.data = new char[h][w];

        for (int the_h = 0; the_h < h; the_h++) {
            for (int the_w = 0; the_w < w; the_w++) {
                char c = chars[the_h*w + the_w];
                this.data[the_h][the_w] = c;
            }
        }
    }

    public int countChars(char c) {
        return StringUtils.countMatches(layerData, c);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : data) {
            builder.append(row);
            builder.append("\n");
        }

        return builder.toString();
    }

    public void merge(Layer other) {
        for (int h = 0; h < height; h++)
            for (int w = 0; w < width; w++) {
                if (this.data[h][w] == '2') {
                    this.data[h][w] = other.data[h][w];
                }
            }
    }
}