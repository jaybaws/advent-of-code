package org.jaybaws.adventofcode.y2019.day7;
import org.jaybaws.adventofcode.*;
import java.util.stream.Collectors;
import java.util.*;

public class Y2019D7Puzzle extends BasePuzzle {

    public Y2019D7Puzzle(List<String> altInput) { super(altInput); }

    public Y2019D7Puzzle() { super(); }

    @Override
    protected void prepare() { }

    @Override
    public Integer solution1() {
        List<List<Integer>> perm = generatePerm(Arrays.stream(new int[] { 0, 1, 2, 3, 4 }).boxed().collect(Collectors.toList()));

        int maxOutput = 0;
        for (List<Integer> p : perm) {
            int[] phases = new int[p.size()];
            for (int i = 0; i < p.size(); i++)
                phases[i] = p.get(i);

            Thruster t = new LinearThruster(phases);
            int output = t.getOutput();
            if (output > maxOutput)
                maxOutput = output;
        }

        return maxOutput;
    }

    @Override
    public Integer solution2() {
        List<List<Integer>> perm = generatePerm(Arrays.stream(new int[] { 5, 6, 7, 8, 9 }).boxed().collect(Collectors.toList()));

        int maxOutput = 0;
        for (List<Integer> p : perm) {
            int[] phases = new int[p.size()];
            for (int i = 0; i < p.size(); i++)
                phases[i] = p.get(i);

            Thruster t = new FeedbackThruster(phases);
            int output = t.getOutput();
            if (output > maxOutput)
                maxOutput = output;
        }

        return maxOutput;
    }

    private <E> List<List<E>> generatePerm(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2019D7Puzzle();

        System.out.println(String.format("What is the highest signal that can be sent to the thrusters? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the highest signal that can be sent to the thrusters? Well, this: %d.", puzzle.solution2()));
    }

}