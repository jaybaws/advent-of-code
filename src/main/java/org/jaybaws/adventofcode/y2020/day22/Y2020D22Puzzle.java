package org.jaybaws.adventofcode.y2020.day22;
import org.jaybaws.adventofcode.*;
import java.util.*;
import java.util.stream.Collectors;

public class Y2020D22Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    private Map<String, List<Integer>> deck;

    public Y2020D22Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        deck = new HashMap<String, List<Integer>>();
        List<Integer> cards = null;
        String name = "";
        for (String line : puzzleInput) {
            if (line.matches("\\d+")) {
                cards.add(Integer.parseInt(line));
            } else if (!line.equals("")) {
                name = line.substring(0, line.length() - 1);
                cards = new ArrayList<Integer>();
                deck.put(name, cards);
            }
        }

        System.out.println(deck);
    }

    public Y2020D22Puzzle() {
        super();
    }

    private int positionOfMax(List<Integer> a) {
        int max = a.get(0);
        int index = 0;

        for (int i = 0; i < a.size(); i++)
        {
            if (max < a.get(i))
            {
                max = a.get(i);
                index = i;
            }
        }

        return index;
    }

    @Override
    public Long solution1() {
        boolean isCompleted = false;
        int round = 1;
        while (!isCompleted) {
            List<Integer> drawnCards = deck.values().stream().mapToInt(l -> l.remove(0)).boxed().collect(Collectors.toList());
            int positionOfWinner = positionOfMax(drawnCards);

            int i = 0;
            for (List<Integer> d : deck.values()) {
                if (i == positionOfWinner) {
                    d.addAll(drawnCards.stream().sorted((i1, i2) -> Integer.compare(i2, i1)).collect(Collectors.toList()));
                }
                i++;
            }

            int donePlayers = 0;
            for (List<Integer> d : deck.values()) {
                if (d.size() == 0) {
                    donePlayers++;
                    // remove the player from the game???
                }
            }

            isCompleted = (donePlayers == deck.size() - 1);
            round++;
        }

        System.out.println("Game ended after round: " + round);

        int maxScore = 0;
        for (String n : deck.keySet()) {
            List<Integer> c = deck.get(n);
            Collections.reverse(c);

            int score = 0;
            for (int i = 1; i <= c.size(); i++) {
                score += i * c.get(i - 1);
            }

            if (score > maxScore)
                maxScore = score;

            System.out.println(n + " scored " + score);
        }

        return (long) maxScore;
    }

    @Override
    public Long solution2() {
        return null; // @TODO!
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D22Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1())); // 32211 = too high!
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}