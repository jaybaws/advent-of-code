package org.jaybaws.adventofcode.y2020.day12;

public class Position {

    private Integer x = 0;
    private Integer y = 0;
    private Direction heading = Direction.E;

    public Position(Integer x, Integer y, Direction h) {
        this.x = x;
        this.y = y;
        this.heading = h;
    }

    public void moveX(Integer x_distance) {
        this.x += x_distance;
    }

    public void moveY(Integer y_distance) {
        this.y += y_distance;
    }

    public void turnRight(Integer degrees) {
        turn("NESWNESW", degrees);
    }

    public void turnLeft(Integer degrees) {
        turn("NWSENWSE", degrees);
    }

    public void rotateLeft(Integer degrees) {
        int cur_x = x;
        int cur_y = y;

        switch (degrees / 90) {
            case 1:
                x = -1*cur_y;
                y = cur_x;
                break;
            case 2:
                x *= -1;
                y *= -1;
                break;
            case 3:
                x = cur_y;
                y = -1 * cur_x;
                break;
        }
    }

    public void rotateRight(Integer degrees) {
        int cur_x = x;
        int cur_y = y;

        switch (degrees / 90) {
            case 1:
                x = cur_y;
                y = -1 * cur_x;
                break;
            case 2:
                y *= -1;
                x *= -1;
                break;
            case 3:
                x = -1 * cur_y;
                y = cur_x;
                break;
        }
    }

    private void turn(String order, Integer degrees) {
        Integer d = degrees / 90;
        int currentHeadingPos = order.indexOf(this.heading.name());
        String newHeadingName = order.substring(currentHeadingPos + d, currentHeadingPos + d + 1);
        this.heading = Direction.valueOf(newHeadingName);
    }

    public Direction getHeading() {
        return this.heading;
    }

    public Integer getManhattanDistance(Integer x, Integer y) {
        return Math.abs(x - this.x) + Math.abs(y - this.y);
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

}