package org.jaybaws.adventofcode.y2021.day11;

import java.util.HashSet;
import java.util.Set;

public class Octopus {

    private int energyLevel;
    private boolean hasFlashed = false;
    private long totalFlashes = 0;
    private Set<Octopus> neighbours = new HashSet<Octopus>();

    public Octopus(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    private Set<Octopus> getNeighbours() {
        return this.neighbours;
    }

    public void tryFlash() {
        if (!this.hasFlashed) {
            if (this.energyLevel > 9) {
                this.totalFlashes++;
                this.hasFlashed = true; // FLASH! AH-HAAAAAA!

                for (Octopus neighbour : this.getNeighbours()) {
                    neighbour.radiate();
                }
            }
        } else {
            // System.out.println("Octoopus has already flashed!");
        }
    }

    public void tryDischarge() {
        if (this.hasFlashed)
            this.energyLevel = 0;
    }

    public long getTotalFlashes() {
        return this.totalFlashes;
    }

    public void radiate() {
        this.energyLevel++;
        this.tryFlash();
    }

    public void charge() {
        this.hasFlashed = false; // New round, reset!
        this.energyLevel++;
    }

    public void addNeighbour(Octopus o) {
        this.neighbours.add(o);
    }

    @Override
    public String toString() {
        if (this.hasFlashed) {
            return "\u001B[34m" + this.energyLevel + "\u001B[0m";
        } else {
            return "\u001B[37m" + this.energyLevel + "\u001B[0m";
        }
    }

    public boolean hasFlashed() {
        return this.hasFlashed;
    }
}