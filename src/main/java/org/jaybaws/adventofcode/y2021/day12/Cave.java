package org.jaybaws.adventofcode.y2021.day12;
import org.apache.commons.lang3.StringUtils;
import java.util.HashSet;
import java.util.Set;

public class Cave {

    private String name;
    private Set<Cave> neighbours = new HashSet<Cave>();
    private int visitLimit;

    public Cave(String name) {
        this(name, 1);
    }

    public Cave(String name, int limit) {
        this.name = name;
        this.visitLimit = limit;
    }

    public void addNeighbour(Cave c) {
        this.neighbours.add(c);
    }

    public Set<Cave> getNeighbours() {
        return this.neighbours;
    }

    public boolean isLarge() {
        return StringUtils.isAllUpperCase(this.name);
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    public int getLimit() {
        if ( this.name.equals("start") || this.name.equals("end") ) {
            return 1;
        } else {
            if (this.isLarge()) {
                return Integer.MAX_VALUE;
            } else {
                return this.visitLimit;
            }
        }
    }
}
