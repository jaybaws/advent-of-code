package org.jaybaws.adventofcode.y2020.day12;

public enum Direction {

    N(0, 1,"W", "E"),
    E(1, 0, "N", "S"),
    S(0, -1, "E", "W"),
    W(-1, 0, "S", "N");

    private Integer x_offset;
    private Integer y_offset;
    private String left;
    private String right;

    private Direction(Integer x, Integer y, String l, String r) {
        this.x_offset = x;
        this.y_offset = y;
        this.left = l;
        this.right = r;
    }

    public Direction left() {
        return Direction.valueOf(left);
    }

    public Direction right() {
        return Direction.valueOf(right);
    }

    public Integer getXOffset() {
        return this.x_offset;
    }

    public Integer getYOffset() {
        return this.y_offset;
    }

}