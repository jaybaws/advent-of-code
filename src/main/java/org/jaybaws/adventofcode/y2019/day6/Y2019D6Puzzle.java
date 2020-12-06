package org.jaybaws.adventofcode.y2019.day6;
import org.jaybaws.adventofcode.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Y2019D6Puzzle extends BasePuzzle {

    private Map<String, SpaceObject> spaceObjects;

    public Y2019D6Puzzle(List<String> altInput) { super(altInput); }

    public Y2019D6Puzzle() { super(); }

    @Override
    protected void prepare() {
        spaceObjects = new HashMap<String, SpaceObject>();

        // First, create all objects
        for (String line : puzzleInput) {
            for (String o : line.split("\\)"))
                spaceObjects.put(o, new SpaceObject(o));
        }

        // Then, assign the orbiting object.
        for (String line : puzzleInput) {
            String[] pair = line.split("\\)");

            SpaceObject center = spaceObjects.get(pair[0]);
            SpaceObject orbiter = spaceObjects.get(pair[1]);

            orbiter.orbit(center);
        }
    }

    @Override
    public Integer solution1() {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        spaceObjects.forEach( (String k, SpaceObject v) -> count.set(count.get() + (v.isOrbitting() ? v.getOrbitDepth() : 0)));

        return count.get();
    }

    @Override
    public Object solution2() {
        // Find the full path back from our targets (YOU and SAN) to the origin (COM)
        List<SpaceObject> you_walk = spaceObjects.get("YOU").getWalkToCenter();
        List<SpaceObject> san_walk = spaceObjects.get("SAN").getWalkToCenter();

        // Determine the shared path they share
        Set<SpaceObject> overlap = you_walk.stream()
                .distinct()
                .filter(san_walk::contains)
                .collect(Collectors.toSet());

        /* To do orbital transfer, we don't need to go all-the-way back to COM, but we can jump on the other path
           from the nearest overlapping point. */
        return (you_walk.size() - overlap.size()) + (san_walk.size() - overlap.size());
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2019D6Puzzle();

        System.out.println(String.format("The total number of direct and indirect orbits = %s.", puzzle.solution1().toString()));
        System.out.println(String.format("The minimum number of orbital transfers required to move from YOU to SAN = %s.", puzzle.solution2().toString()));
    }

}