package org.jaybaws.adventofcode.y2020.day7;

public class BagContents {

    private Bag bag;
    private int count;

    public BagContents(Bag b, int c) {
        bag = b;
        count = c;
    }

    public Bag getBag() {
        return bag;
    }

    public int getCount() {
        return count;
    }

}
