package org.jaybaws.adventofcode.y2020.day13;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Y2020D13Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D13Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D13Puzzle() { super(); }

    @Override
    protected void prepare() {
    }

    @Override
    public Integer solution1() {
        Integer estimate = Integer.valueOf(puzzleInput.get(0));

        List<Bus> busses = new ArrayList<Bus>();
        for (String b : puzzleInput.get(1).split(",")) {
            if (!b.equals("x")) {
                Bus theBus = new Bus(b);
                busses.add(theBus);

                if (TRACE)
                    System.out.println(String.format("%s has wait-time %d.", theBus, theBus.waitTime(estimate)));
            }
        }

        Bus theBus = busses.stream().sorted((Bus b1, Bus b2) -> Integer.compare(b1.waitTime(estimate), b2.waitTime(estimate))).findFirst().get();
        return theBus.getId() * theBus.waitTime(estimate);
    }

    @Override
    public BigInteger solution2() {
        String[] busses = puzzleInput.get(1).split(",");

        BigInteger time = BigInteger.ZERO;
        BigInteger step = BigInteger.ONE;

        for (int offset = 0; offset < busses.length; offset++) {
            if (!busses[offset].equals("x")) {
                BigInteger bus = new BigInteger(busses[offset]);

                while (!time.add(BigInteger.valueOf(offset)).remainder(bus).equals(BigInteger.ZERO)) {
                    time = time.add(step);
                }

                step = step.multiply(bus);
            }
        }

        return time;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D13Puzzle();

        System.out.println(String.format("What is the ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the earliest timestamp such that all of the listed bus IDs depart at offsets matching their positions in the list?\n--> Well, this: %d.", puzzle.solution2()));
    }

}