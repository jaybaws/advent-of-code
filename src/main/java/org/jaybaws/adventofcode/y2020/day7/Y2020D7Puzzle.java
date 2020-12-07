package org.jaybaws.adventofcode.y2020.day7;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;
import java.util.*;

public class Y2020D7Puzzle extends BasePuzzle {

    private final boolean TRACE = false;

    public Y2020D7Puzzle(List<String> altInput) { super(altInput); }

    public Y2020D7Puzzle() { super(); }

    protected Map<String, Bag> bags;

    @Override
    protected void prepare() {
        bags = new HashMap<String, Bag>();
        for (String line : puzzleInput) {
            String[] splitLine = line.split(" bags contain ");
            String bagColor =  splitLine[0];
            String bagContents = splitLine[1];
            Bag thisBag = new Bag(bagColor);
            if (!bags.containsKey(bagColor)) {
                bags.put(bagColor, thisBag);
            } else {
                thisBag = bags.get(bagColor);
            }

            if (!bagContents.equals("no other bags."))
                for (String bagContentLine : bagContents.split(", ")) {
                    int count = Integer.valueOf(StringUtils.substringBefore(bagContentLine, " "));
                    String containedBagColor = StringUtils.substringAfter(StringUtils.substringBefore(bagContentLine, " bag"), " ");

                    Bag containedBag = bags.get(containedBagColor);
                    if (containedBag == null) {
                        containedBag = new Bag(containedBagColor);
                        bags.put(containedBagColor, containedBag);
                    }

                    thisBag.add(containedBag, count);

                    if (TRACE)
                        System.out.println(String.format("%s contains %d bags of color %s.", thisBag, count, containedBagColor));
                }
        }
    }

    @Override
    public Integer solution1() {
        int possibilities = 0;
        for (Bag b : bags.values()) {
            if (b.canHold("shiny gold")) {
                possibilities++;
            }
        }
        return possibilities;
    }

    @Override
    public Integer solution2() {
        Bag myBag = bags.get("shiny gold");
        return myBag.subBagCount();
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D7Puzzle();

        System.out.println(String.format("How many bag colors can eventually contain at least one shiny gold bag? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("How many individual bags are required inside your single shiny gold bag? Well, this: %d.", puzzle.solution2()));
    }

}