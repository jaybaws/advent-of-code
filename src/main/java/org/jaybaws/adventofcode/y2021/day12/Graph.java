package org.jaybaws.adventofcode.y2021.day12;
import java.util.*;

public class Graph {

    Map<String, Cave> uniqueCaves = new HashMap<String, Cave>();
    private int limit;


    public Graph(List<String> routes) {
        this(routes, 1);
    }

    public Graph(List<String> routes, int limit) {
        this.limit = limit;
        for (String route : routes) {
            String nameA = route.split("-")[0];
            String nameB = route.split("-")[1];

            Cave caveA = new Cave(nameA, limit);
            this.uniqueCaves.put(nameA, caveA);

            Cave caveB = new Cave(nameB, limit);
            this.uniqueCaves.put(nameB, caveB);
        }

        for (String route : routes) {
            String nameA = route.split("-")[0];
            String nameB = route.split("-")[1];

            Cave caveA = this.uniqueCaves.get(nameA);
            Cave caveB = this.uniqueCaves.get(nameB);

            caveA.addNeighbour(caveB);
            caveB.addNeighbour(caveA);
        }
    }

    private boolean isStuck(Cave current, Cave target, Stack<Cave> path, Set<Cave> seen) {
        if (current.equals(target))
            return false;

        for (Cave neighbour : current.getNeighbours()) {
            if (!seen.contains(neighbour) || isAllowed(path, neighbour)) {
                seen.add(neighbour);

                if (!isStuck(neighbour, target, path, seen))
                    return false;
            }
        }

        return true;
    }

    private void search(Cave current, Cave target, Stack<Cave> path, Set<Cave> seen, List<Stack<Cave>> up) {
        if (current.equals(target)) {
            List<String> pathStringList = new ArrayList<String>();
            for (Cave c : path) {
                pathStringList.add(c.toString());
            }
            Collections.sort(pathStringList);
            // System.out.println("Got a path: " + path);
            up.add(path);
            return;
        }

        seen.clear();
        seen.addAll(path);

        if (isStuck(current, target, path, seen))
            return;

        for (Cave neighbour : current.getNeighbours()) {
            if (!path.contains(neighbour) || isAllowed(path, neighbour)) {
                path.push(neighbour);
                search(neighbour, target, path, seen, up);
                path.pop();
            }
        }
    }

    private long walks(String a, String b) {
        List<Stack<Cave>> uniquePaths = new ArrayList<Stack<Cave>>();

        Stack<Cave> path = new Stack<Cave>();
        Set<Cave> seen = new HashSet<Cave>();

        Cave beginCave = this.uniqueCaves.get(a);
        Cave endCave = this.uniqueCaves.get(b);

        path.add(beginCave);
        seen.add(beginCave);

        search(beginCave, endCave, path, seen, uniquePaths);

        return uniquePaths.size();
    }

    public long walks() {
        return walks("start", "end");
    }

    private boolean isAllowed(Collection<Cave> path, Cave c) {
        if (c.toString().equals("start") || c.toString().equals("end"))
            return false;

        if (limit > 1) {
            if (c.isLarge())
                return true;
            boolean allowed = true;
            for (Cave x : path.toArray(new Cave[] {})) {
                if (!x.isLarge()) {
                    int occurrences = Collections.frequency(path, x);
                    if (occurrences > 1)
                        allowed = false;
                }
            }
            return allowed;
        } else {
            return (c.isLarge());
        }
    }
}
