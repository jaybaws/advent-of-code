package org.jaybaws.adventofcode.y2020.day12;

public interface Move {

    public void doMove(Position p);
    public void doMove(Position ship, Position waypoint);

    public static Move fromString(String s) {
        String m = s.substring(0, 1);
        Integer distance = Integer.valueOf(s.substring(1,s.length()));

        switch (m) {
            case "N": return new North(distance);
            case "E": return new East(distance);
            case "S": return new South(distance);
            case "W": return new West(distance);
            case "F": return new Forward(distance);
            case "L": return new Left(distance);
            case "R": return new Right(distance);
            default: throw new IllegalArgumentException(String.format("Unrecognized move '%s'.", m));
        }
    }
}