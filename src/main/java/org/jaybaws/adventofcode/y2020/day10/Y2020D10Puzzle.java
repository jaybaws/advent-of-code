package org.jaybaws.adventofcode.y2020.day10;
import org.jaybaws.adventofcode.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Y2020D10Puzzle extends BasePuzzle {

    private static boolean TRACE = true;

    public Y2020D10Puzzle(List<String> altInput) {
        super(altInput);
    }

    public Y2020D10Puzzle() { super(); }

    protected List<Integer> data;

    private void addDifference(Integer difference, Map<Integer, Integer> diffList) {
        if (diffList.containsKey(difference)) {
            diffList.put(difference, diffList.get(difference) + 1);
        } else {
            diffList.put(difference, 1);
        }
    }

    @Override
    protected void prepare() {
        data = puzzleInput.stream().map(Integer::valueOf).sorted().collect(Collectors.toList());
    }

    @Override
    public Integer solution1() {
        Map<Integer, Integer> differences = new HashMap<Integer, Integer>();
        for (int i = 0; i < data.size(); i++) {
            Integer diff = (i == 0) ? data.get(i) : data.get(i) - data.get(i - 1);
            addDifference(diff, differences);
        }
        addDifference(3, differences);

        return differences.get(1) * differences.get(3);
    }

    @Override
    public BigInteger solution2() {
        List<Integer> dots = new ArrayList<Integer>(data);
        dots.add(0);
        dots = dots.stream().sorted().collect(Collectors.toList());

        BigInteger[] dp = new BigInteger[dots.size()];
        dp[0] = BigInteger.ONE;
        dp[1] = BigInteger.ONE;

        for (int i = 2; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dots.get(i) - dots.get(j)  <= 3) {
                    BigInteger a = (dp[i] == null) ? BigInteger.ZERO : dp[i];
                    BigInteger b = (dp[j] == null) ? BigInteger.ZERO : dp[j];
                    dp[i] = a.add(b);
                } else {
                    break;
                }
            }
        }

        return dp[dp.length - 1];

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D10Puzzle();

        System.out.println(String.format("What is the number of 1-jolt differences multiplied by the number of 3-jolt differences? Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device? Well, this: %d.", puzzle.solution2()));
    }

}