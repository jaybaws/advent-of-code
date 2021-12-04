package org.jaybaws.adventofcode.y2021.day4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BingoBoard {

    private int[][] values = new int[5][5];
    private List<Integer> markedValues = new ArrayList<Integer>();

    public BingoBoard(List<String> input) {
        int r = 0;
        for (String row : input) {
            for (int c = 0; c < 5; c++) {
                String valStr = row.substring(c*3, c*3+2);
                int val = Integer.parseInt(valStr.trim());
                values[r][c] = val;
            }
            r++;
        }
    }

    private List<Integer> getColumn(int c) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < 5; i++) {
            result.add(this.values[i][c]);
        }

        return result;
    }

    private List<Integer> getRow(int r) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < 5; i++) {
            result.add(this.values[r][i]);
        }

        return result;
    }

    private List<Integer> getAll() {
        List<Integer> result = new ArrayList<Integer>();
        for (int c = 0; c < 5; c++)
            for (int r = 0; r < 5; r++)
                result.add(this.values[r][c]);

        return result;
    }

    private boolean hasWon() {
        for (int c = 0; c < 5; c++)
            if (this.markedValues.containsAll(this.getColumn(c))) {
                // System.out.println("Vertical win!");
                return true; // A vertical sweep!
            }

        for (int r = 0; r < 5; r++)
            if (this.markedValues.containsAll(this.getRow(r))) {
                // System.out.println("Horinzontal win!");
                return true; // A horizontal sweep!
            }

        return false;
    }

    public long score() {
        int lastMarkedValue = this.markedValues.get(this.markedValues.size() - 1);

        List<Integer> allUnmarkedValues = new ArrayList<Integer>(this.getAll());
        allUnmarkedValues.removeAll(this.markedValues);

        int sumOfUnmarkedValues = allUnmarkedValues.stream().reduce(0, Integer::sum);

        // System.out.println("All marked values: " + this.markedValues.toString());
        // System.out.println("lastMarkedValue: " + lastMarkedValue);
        // System.out.println("unmarked values: " + allUnmarkedValues.toString() + " sum: " + sumOfUnmarkedValues);

        return lastMarkedValue * sumOfUnmarkedValues;
    }

    public boolean mark(int val) {
        this.markedValues.add(val);
        return hasWon();
    }

    public boolean mark(List<Integer> vals) {
        for (Integer val : vals)
            this.mark(val);
        return hasWon();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                int val = this.values[r][c];
                String valStr = String.format("%03d", val);
                if (this.markedValues.contains(val)) {
                    b.append("[" + valStr + "] ");
                } else {
                    b.append("(" + valStr + ") ");
                }
            }
            b.append("\n");
        }
        return b.toString();
    }

}
