package org.jaybaws.adventofcode.y2021.day6;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Y2021D6Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    private List<Integer> getFish() {
        return Arrays.stream(this.puzzleInput.get(0).split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public Y2021D6Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {

    }

    public Y2021D6Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        return solution1(80);
    }

    public Long solution1(int days) {
        List<Integer> fishes = this.getFish();

        if (TRACE)
            System.out.println("Initial state: " + fishes);

        for (int day = 1; day <= days; day++) {
            int fishCount = fishes.size();
            for (int f = 0; f < fishCount; f++)  {
                int timer = fishes.get(f);
                if (timer == 0) {
                    fishes.set(f, 6);
                    fishes.add(8);
                } else {
                    fishes.set(f, timer - 1);
                }
            }

            if (TRACE)
                System.out.println("After " + day + " days: " + fishes);

        }

        return (long) fishes.size();
    }

    @Override
    public BigInteger solution2() {
        final int days = 256;
        BigInteger totalCount = BigInteger.ZERO;

        Map<Integer, BigInteger> fishes = new HashMap<Integer, BigInteger>();
        for (int i = 0; i <= 8; i++)
            fishes.put(Integer.valueOf(i), BigInteger.ZERO);

        for (Integer timer : this.getFish()) {
            BigInteger bucket = fishes.get(timer);
            fishes.replace(timer, bucket.add(BigInteger.ONE));
        }

        if (TRACE)
            System.out.println("Initial  state,                 fishes: " + fishes);

        for (int day = 1; day <= days; day++) {
            Map<Integer, BigInteger> newPopulation = new HashMap<Integer, BigInteger>();
            for (int bucket = 8; bucket >= 0; bucket--) {
                BigInteger bucketSize = fishes.get(bucket);
                if (bucket == 0) {
                    BigInteger sixBucketSize = newPopulation.get(6);
                    newPopulation.put(6, bucketSize.add(sixBucketSize));
                    newPopulation.put(8, bucketSize);

                } else {
                    Integer newBucket = bucket - 1;
                    newPopulation.put(newBucket, bucketSize);
                }
            }
            fishes = newPopulation;

            totalCount = BigInteger.ZERO;
            for (BigInteger bucket : fishes.values()) {
                totalCount = totalCount.add(bucket);
            }

            if (TRACE)
                System.out.println(String.format("After %03d days, fishCount = %03d fishes: %s.", day, totalCount, fishes.toString()));

        }

        return totalCount;
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D6Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}