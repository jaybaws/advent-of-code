package org.jaybaws.adventofcode.y2021.day5;
import java.util.HashMap;

public class Map {

    private HashMap<String, Integer> touches = new HashMap<String, Integer>();

    public void addLine(int x1, int y1, int x2, int y2, boolean onlyStraights) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int ix = (dx == 0) ? 0:dx/Math.abs(dx);
        int iy = (dy == 0) ? 0:dy/Math.abs(dy);

        int l = Math.max(Math.abs(dx), Math.abs(dy));

        boolean isStraight = (ix == 0 ^ iy == 0);

        if (!onlyStraights || (onlyStraights && isStraight)) {
            for (int x = x1, y = y1, d = 0; d <= l; x += ix, y += iy, d++)
                touchPoint(x, y);
        }
    }

    private void touchPoint (int x, int y) {
        String key = String.format("(%d,%d)", x, y);
        if (this.touches.containsKey(key)) {
            Integer oldValue = this.touches.get(key);
            this.touches.replace(key, oldValue + 1);
        } else {
            this.touches.put(key, 1);
        }
    }


    public long getTwoOrMoreCrossing() {
        long result = 0;
        for (Integer count : this.touches.values()) {
            if (count >= 2)
                result++;
        }

        return result;
    }


}
