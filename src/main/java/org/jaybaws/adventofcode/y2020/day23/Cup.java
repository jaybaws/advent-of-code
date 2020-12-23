package org.jaybaws.adventofcode.y2020.day23;

public class Cup {

    public final long value;
    public Cup next;
    public Cup previous;

    public Cup(Long value) {
        this.value = value;
    }

    public Cup(Long value, Cup previous) {
        this(value);
        this.previous = previous;
    }

    public void setNext(Cup next) {
        this.next = next;
    }

    public void setPrevious(Cup previous) {
        this.previous = previous;
    }

}