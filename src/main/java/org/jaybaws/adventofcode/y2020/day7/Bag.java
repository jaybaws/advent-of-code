package org.jaybaws.adventofcode.y2020.day7;
import java.util.HashMap;
import java.util.Map;

public class Bag  {

    private final String color;
    private final Map<String, BagContents> contents = new HashMap<String, BagContents>();

    public Bag(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void add(Bag b, int count) {
        if (this.contents.containsKey(b))
            throw new IllegalArgumentException(String.format("%s already part of the contents of %s!", b, this));

        this.contents.put(b.getColor(), new BagContents(b, count));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bag) {
            Bag bagObject = (Bag) obj;
            return bagObject.getColor().equals(this.getColor());
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("%s bag", this.getColor());
    }

    public boolean canHold(String color) {
        if (this.contents.isEmpty()) {
            return false;
        } else {
            if (this.contents.containsKey(color)) {
                return true;
            } else {
                for (BagContents subBagContents : this.contents.values()) {
                    if (subBagContents.getBag().canHold(color)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public int subBagCount() {
        if (this.contents.isEmpty()) {
            return 0;
        } else {
            int sumOfSubs = 0;
            for (BagContents bc : this.contents.values()) {
                sumOfSubs += bc.getCount();
                sumOfSubs += bc.getCount() * bc.getBag().subBagCount();
            }
            return sumOfSubs;
        }
    }

}