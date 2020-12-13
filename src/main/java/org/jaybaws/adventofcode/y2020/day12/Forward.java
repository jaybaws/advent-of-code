package org.jaybaws.adventofcode.y2020.day12;

public class Forward implements Move {

    private Integer distance;

    public Forward(Integer d) {
        this.distance = d;
    }

    @Override
    public void doMove(Position p) {
        p.moveX(p.getHeading().getXOffset() * distance);
        p.moveY(p.getHeading().getYOffset() * distance);
    }

    @Override
    public void doMove(Position ship, Position waypoint) {
        ship.moveX(distance * waypoint.getX());
        ship.moveY(distance * waypoint.getY());
    }

}