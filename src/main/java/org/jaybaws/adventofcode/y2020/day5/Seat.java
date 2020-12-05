package org.jaybaws.adventofcode.y2020.day5;

public class Seat {

    private final int row;
    private final int col;

    public Seat(String bsp) {

        int r = 0;
        int mask = 64;
        for (int i = 0; i < 7; i++) {
            if (bsp.charAt(i) == 'B')
                r = r ^ mask >> i;
        }

        int c = 0;
        mask = 4;
        for (int i = 7; i < 10; i++) {
            if (bsp.charAt(i) == 'R')
                c = c ^ mask >> (i - 7);
        }

        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public int getSeatId() {
        return (getRow() * 8) + getColumn();
    }

    @Override
    public String toString() {
        return String.format("Seat(%d) has row(%d), col(%d).", getSeatId(), getRow(), getColumn());
    }
}
