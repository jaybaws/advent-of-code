package org.jaybaws.adventofcode.y2019.day8;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2019D8Puzzle extends BasePuzzle {

    private final int width;
    private final int height;
    private final List<Layer> layers = new ArrayList<Layer>();

    public Y2019D8Puzzle(int width, int height, List<String> altInput) {
        super(altInput);
        this.width = width;
        this.height = height;
    }

    public Y2019D8Puzzle() {
        super();
        this.width = 25;
        this.height = 6;
    }

    @Override
    protected void prepare() {

    }

    private void ourPrepare() {
        String data = puzzleInput.get(0);
        int layerSize = width * height;
        int totalLayers = data.length() / layerSize;

        for (int l = 0; l < totalLayers; l++) {
            int beginIndex = l * layerSize;
            Layer theLayer = new Layer(data.substring(beginIndex, beginIndex + layerSize), width, height);
            layers.add(theLayer);
        }
    }

    private int countChar(char[] chars, char m) {
        int count = 0;
        for (char c : chars) {
            if (c == m)
                count++;
        }
        return count;
    }

    @Override
    public Integer solution1() {
        ourPrepare();

        Layer theLayer = null;
        int lowest = (width*height) + 1; // Start with a safe value!
        for (Layer l : layers) {
            int count = l.countChars('0');
            if (count < lowest) {
                theLayer = l;
                lowest = count;
            }
        }

        return theLayer.countChars('1') * theLayer.countChars('2');
    }

    @Override
    public String solution2() {
        ourPrepare();

        Layer output = new Layer(StringUtils.repeat('2', width*height), width, height);
        for (Layer l : layers) {
            output.merge(l);
        }

        return StringUtils.replace(output.toString(), "0", " ");
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2019D8Puzzle();

        System.out.println(String.format("On that layer (the one with the fewest zeroes), what is the number of 1 digits multiplied by the number of 2 digits? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What message is produced after decoding your image? Well, this:\n\n%s", puzzle.solution2()));
    }

}