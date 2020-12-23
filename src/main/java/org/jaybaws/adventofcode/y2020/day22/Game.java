package org.jaybaws.adventofcode.y2020.day22;
import java.util.*;

public class Game {

    private static final boolean TRACE = false;

    private int game;
    private List<Integer> p1cards;
    private List<Integer> p2cards;
    private Set<String> history = new HashSet<String>();

    public Game(int game, List<Integer> p1cards, List<Integer> p2cards) {
        this.game = game;
        this.p1cards = p1cards;
        this.p2cards = p2cards;
    }

    public long winnersScore() {
        List<Integer> c = (p1cards.size() > 0) ? p1cards : p2cards;
        Collections.reverse(c);

        long score = 0;
        for (int i = 1; i <= c.size(); i++) {
            score += i * c.get(i - 1);
        }

        return score;
    }

    public int play() {
        if (TRACE)
            System.out.println(String.format("\n=== Game %d ===", game));

        int round = 0;
        boolean isCompleted = false;

        while (!isCompleted) {
            round++;

            if (TRACE) {
                System.out.println(String.format("\n-- Round %d (Game %d) -- ", round, game));
                System.out.println(String.format("Player 1's deck: %s", p1cards));
                System.out.println(String.format("Player 2's deck: %s", p2cards));
            }

            String playId = p1cards.toString() + p2cards.toString();
            if (history.contains(playId)) return 1; else history.add(playId);

            Integer p1card = p1cards.remove(0);
            Integer p2card = p2cards.remove(0);
            if (TRACE) {
                System.out.println(String.format("Player 1 plays: %d", p1card));
                System.out.println(String.format("Player 2 plays: %d", p2card));
            }

            int roundWinner = 0;
            if (p1cards.size() >= p1card && p2cards.size() >= p2card) {
                // RECURSE MOTHERFUCKER!

                List<Integer> p1SubGameCards = new ArrayList<Integer>(p1cards.subList(0, p1card));
                List<Integer> p2SubGameCards = new ArrayList<Integer>(p2cards.subList(0, p2card));

                Game subGame = new Game(game + 1, p1SubGameCards, p2SubGameCards);
                if (TRACE)
                    System.out.println("Playing a sub-game to determine the winner...");
                roundWinner = subGame.play();
            } else {
                roundWinner = (p1card > p2card) ? 1 : 2;
            }

            if (TRACE)
                System.out.println(String.format("Player %d wins round %d of game %d!", roundWinner, round, game));

            switch (roundWinner) {
                case 1:
                    p1cards.add(p1card);
                    p1cards.add(p2card);
                    break;
                case 2:
                    p2cards.add(p2card);
                    p2cards.add(p1card);
                    break;
            }

            isCompleted = (p1cards.size() == 0 || p2cards.size() == 0);
        }

        return (p1cards.size() > 0) ? 1 : 2;
    }

}