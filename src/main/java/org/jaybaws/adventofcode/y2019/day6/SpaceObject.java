package org.jaybaws.adventofcode.y2019.day6;
import java.util.LinkedList;
import java.util.List;

public class SpaceObject {

    private final String name;
    private SpaceObject orbittingObject;

    public SpaceObject(String n) {
        name = n;
    }

    public boolean isOrbitting() {
        return (orbittingObject != null);
    }

    public int getOrbitDepth() {
        if (isOrbitting()) {
            return 1 + orbittingObject.getOrbitDepth();
        } else {
            return 0;
        }
    }

    public void orbit(SpaceObject o) {
        orbittingObject = o;
    }

    public List<SpaceObject> getWalkToCenter() {
        List<SpaceObject> walk = new LinkedList<SpaceObject>();

        if (isOrbitting()) {
            walk.add(orbittingObject);
            walk.addAll(orbittingObject.getWalkToCenter());
        }

        return walk;
    }

    public String toString() { return name; }

}