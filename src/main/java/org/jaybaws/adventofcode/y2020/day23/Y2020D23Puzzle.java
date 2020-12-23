package org.jaybaws.adventofcode.y2020.day23;
import org.apache.commons.lang3.StringUtils;
import org.jaybaws.adventofcode.*;
import java.math.BigInteger;
import java.util.*;

public class Y2020D23Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2020D23Puzzle(List<String> altInput) {
        super(altInput);
    }

    private String loadedLabels;

    @Override
    protected void prepare() {
        loadedLabels = puzzleInput.get(0);
    }

    public Y2020D23Puzzle() {
        super();
    }

    private static String cupList(String list, char mark) {
        String result = "";

        for (char c : list.toCharArray())
            if (c == mark)
                result += "(" + c + ")";
            else
                result += " " + c + " ";

        return result;
    }

    private static char determineDestinationCup(String labels, char currentCup, String pickedUp) {
        String regex = String.format("[%s]", pickedUp);
        String scan = "123456789123456789".replaceAll(regex, "");
        return scan.charAt(scan.lastIndexOf(currentCup) - 1);
    }

    public String solution1(int depth) {
        String labels = loadedLabels;
        char currentCup = labels.charAt(0);
        for (int move = 1; move <= depth; move++) {
            String pickUp = StringUtils.substringAfter(labels + labels, currentCup).substring(0, 3);

            char destinationCup = determineDestinationCup(labels, currentCup, pickUp);

            if (TRACE) {
                System.out.println(String.format("\n-- move %d --", move));
                System.out.println(String.format("cups: %s", cupList(labels, currentCup)));
                System.out.println(String.format("pick up: %s", String.join(", ", pickUp.split(""))));
                System.out.println(String.format("destination: %c", destinationCup));
            }

            int pos = labels.indexOf(currentCup);
            labels = StringUtils.replace(labels + labels, pickUp, ""); // remove the picked-up cups
            labels = StringUtils.replace(labels , "" + destinationCup, destinationCup + pickUp); // Put the picked-up cups after destination
            while (labels.indexOf(currentCup) != pos) {
                labels = labels.substring(1) + labels.charAt(0);
            }
            labels = labels.substring(0, 9);

            currentCup = (labels + labels).charAt(labels.indexOf(currentCup) + 1);

        }

        return StringUtils.substringBetween(labels + labels, "1", "1");
    }

    @Override
    public String solution1() {
        return solution1(100);
    }

    private static Map<Long, Cup> buildCupsList(String labels, long size) {
        Map<Long, Cup> cups = new HashMap<Long, Cup>();

        Cup firstCup = null;
        Cup previousCup = null;
        Cup cup = null;
        for (long l = 1; l <= size; l++) {
            long value = l;
            if (l <= labels.length()) {
                value = Long.parseLong("" + labels.charAt((int) l - 1));
            }
            cup = new Cup(value, previousCup);

            if (firstCup == null) firstCup = cup;
            if (previousCup != null) previousCup.setNext(cup);

            cups.put(value, cup);
            previousCup = cup;
        }

        cup.setNext(firstCup);
        firstCup.setPrevious(cup);

        return cups;
    }

    public BigInteger solution2(long cupCount, long moveCount) {
        Map<Long, Cup> cups = buildCupsList(loadedLabels, cupCount);

        Cup currentCup = cups.get(Long.parseLong("" + loadedLabels.charAt(0)));
        for (long move = 1; move <= moveCount; move++) {

            Cup firstTaken = currentCup.next;
            Cup secondTaken = firstTaken.next;
            Cup lastTaken = secondTaken.next;

            List<Long> takenValues = Arrays.asList(new Long[] { firstTaken.value, secondTaken.value, lastTaken.value });

            // Cut the taken cups out of the circle.
            Cup newNeighbour = lastTaken.next;
            currentCup.next = newNeighbour;
            newNeighbour.previous = currentCup;

            // Determine destination for our cut-out cups
            Long destinationValue = (long) currentCup.value - 1;
            while (destinationValue < 1l || takenValues.contains(destinationValue)) {
                if (destinationValue <= 1l) {
                    destinationValue = cupCount;
                } else {
                    destinationValue--;
                }
            }
            Cup destinationCup = cups.get(destinationValue);
            Cup nextDestinationCup = destinationCup.next;

            // Paste the taken cups between destinationCup and nextDestinationCup
            destinationCup.next = firstTaken;
            firstTaken.previous = destinationCup;
            lastTaken.next = nextDestinationCup;
            nextDestinationCup.previous = lastTaken;

            // Determine the next cup to evalutate
            currentCup = currentCup.next;
        }

        Cup cup1 = cups.get(1l);
        BigInteger a = BigInteger.valueOf(cup1.next.value);
        BigInteger b = BigInteger.valueOf(cup1.next.next.value);
        BigInteger result = a.multiply(b);

        return result;
    }

    @Override
    public BigInteger solution2() {
        return solution2(1000000l, 10000000l);
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D23Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}