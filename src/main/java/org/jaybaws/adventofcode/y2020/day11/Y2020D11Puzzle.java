package org.jaybaws.adventofcode.y2020.day11;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.util.*;
import java.util.stream.IntStream;

public class Y2020D11Puzzle extends BasePuzzle {

    private static final boolean TRACE = true;

    private static final char emptySeat = 'L';
    private static final char occupiedSeat = '#';
    private static final char floor = '.';
    private static final char unknown = '?';

    protected char[][] seatmap;

    public Y2020D11Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D11Puzzle() { super(); }

    private static boolean isSeat(char c) {
        return (c == occupiedSeat || c == emptySeat);
    }

    private static boolean isOccupied(char c) {
        return (c == occupiedSeat);
    }

    private static boolean isEmpty(char c) {
        return (!isOccupied(c));
    }

    private static boolean isFloor(char c) {
        return (c == floor);
    }

    private boolean hasNoAdjacentOccupiedSeats(int r, int c) {
        return (occupiedSeatsAdjacent(r, c) == 0);
    }

    private boolean hasNoVisibleOccupiedSeats(int r, int c) {
        return (occupiedVisibleSeats(r, c) == 0);
    }

    private char getSeat(int r, int c) {
        try {
            return seatmap[r][c];
        } catch (Throwable t) { // if ArrayIndexOutOfBounds --> treat it as floor
            return unknown;
        }
    }

    private static String toStr(char[] chars) {
        return new String(chars);
    }



    private int occupiedSeatsAdjacent(int r, int c) {
        char[] adjacent = new char[] {
        getSeat(r-1,c-1),
        getSeat(r-1,c),
        getSeat(r-1,c+1),

        getSeat(r,c-1),
        getSeat(r,c+1),

        getSeat(r+1,c-1),
        getSeat(r+1,c),
        getSeat(r+1,c+1) };

        return (int) IntStream.range(0, adjacent.length).mapToObj(i -> adjacent[i]).filter( item -> isOccupied(item)).count();
    }


    private String walk(int r, int c, int r_inc, int c_inc) {
        String result = ""; int v = r; int h = c;

        while ((v >= 0 && v <= seatmap.length) && (h >= 0 && h <= seatmap[0].length)) {
            v += r_inc;
            h += c_inc;

            try {
                char val = seatmap[v][h];
                if (isSeat(val))
                    result += val;
            } catch (ArrayIndexOutOfBoundsException t) {
                return result;
            }

        }

        return result;
    }

    private int occupiedVisibleSeats(int r, int c) {
        String[] views = new String[] {
                walk(r, c,-1,-1),
                walk(r, c,-1,0),
                walk(r, c,-1,+1),

                walk(r,c, 0,-1),
                walk(r,c, 0,+1),

                walk(r,c,+1,-1),
                walk(r,c,+1,0),
                walk(r,c,+1,+1)
        };

        return (int) Arrays.stream(views).filter(s -> s.startsWith(String.valueOf(occupiedSeat))).count();

    }

    private char valueAfterRules1(int r, int c) {
        char val = getSeat(r, c);
        char newVal = val;

        switch (val) {
            case emptySeat:
                newVal = hasNoAdjacentOccupiedSeats(r, c) ? occupiedSeat : val;
                break;

            case occupiedSeat:
                newVal = occupiedSeatsAdjacent(r, c) >= 4 ? emptySeat : val;
                break;
        }

        return newVal;
    }

    private char valueAfterRules2(int r, int c) {
        char val = getSeat(r, c);
        char newVal = val;

        switch (val) {
            case emptySeat:
                newVal = hasNoVisibleOccupiedSeats(r, c) ? occupiedSeat : val;
                break;

            case occupiedSeat:
                newVal = occupiedVisibleSeats(r, c) >= 5 ? emptySeat : val;
                break;
        }

        return newVal;
    }


    @Override
    protected void prepare() {
        seatmap = new char[puzzleInput.size()][puzzleInput.get(0).length()];
        int r = 0;
        for (String row : puzzleInput) {
            int c = 0;
            for (char val : row.toCharArray()) {
                seatmap[r][c] = val;
                c++;
            }
            r++;
        }
    }

    private void printSeatMap(String header, String footer) {
        System.out.println(header);
        for (int r = 0; r < seatmap.length; r++) {
            for (int c = 0; c < seatmap[r].length; c++) {
                System.out.print(seatmap[r][c]);
            }
            System.out.println("");
        }
        System.out.println(footer);
    }

    private int totalOccupiedSeatCount() {
        int counter = 0;

        for (int r = 0; r < seatmap.length; r++)
            for (int c = 0; c < seatmap[r].length; c++)
                if (isOccupied(getSeat(r, c)))
                    counter ++;

        return counter;
    }

    @Override
    public Integer solution1() {

        if (TRACE)
            printSeatMap("", "--> " + totalOccupiedSeatCount());

        boolean hasChanges = true;
        while (hasChanges) {
            hasChanges = false;

            char[][] newSeatMap = new char[puzzleInput.size()][puzzleInput.get(0).length()];
            for (int r = 0; r < newSeatMap.length; r++) {
                for (int c = 0; c < newSeatMap[r].length; c++) {
                    char val = getSeat(r, c);
                    char newVal = valueAfterRules1(r, c);
                    newSeatMap[r][c] = newVal;
                    if (newVal != val) {
                        hasChanges = true;
                    }
                }
            }
            seatmap = newSeatMap; // Overwrite the existing seatmap

            if (TRACE)
                printSeatMap("", "--> " + totalOccupiedSeatCount());

        }
        return totalOccupiedSeatCount();
    }

    @Override
    public Integer solution2() {

        if (TRACE)
            printSeatMap("", "--> " + totalOccupiedSeatCount());

        boolean hasChanges = true;
        while (hasChanges) {
            hasChanges = false;

            char[][] newSeatMap = new char[puzzleInput.size()][puzzleInput.get(0).length()];
            for (int r = 0; r < newSeatMap.length; r++) {
                for (int c = 0; c < newSeatMap[r].length; c++) {
                    char val = getSeat(r, c);
                    char newVal = valueAfterRules2(r, c);
                    newSeatMap[r][c] = newVal;
                    if (newVal != val) {
                        hasChanges = true;
                    }
                }
            }
            seatmap = newSeatMap; // Overwrite the existing seatmap

            if (TRACE)
                printSeatMap("", "--> " + totalOccupiedSeatCount());
        }

        return totalOccupiedSeatCount();

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D11Puzzle();

        System.out.println(String.format("[q1] How many seats end up occupied? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("[q2] Given the new rules, how many seats end up occupied? Well, this: %d.", puzzle.solution2()));
    }

}