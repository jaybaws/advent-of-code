package org.jaybaws.adventofcode.y2021.day4;
import org.jaybaws.adventofcode.BasePuzzle;
import org.jaybaws.adventofcode.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class Y2021D4Puzzle extends BasePuzzle {

    public static final boolean TRACE = false;

    public Y2021D4Puzzle(List<String> altInput) {
        super(altInput);
    }

    private List<Integer> marks;
    private List<BingoBoard> bingoBoards;

    @Override
    protected void prepare() {
        bingoBoards = new ArrayList<BingoBoard>();

        marks = new ArrayList<Integer>();
        for (String m : this.puzzleInput.get(0).split(",")) {
            marks.add(Integer.parseInt(m));
        }

        int boardCount = (this.puzzleInput.size() - 1) / 6;
        for (int b = 0; b < boardCount; b++) {
            List<String> boardInput = this.puzzleInput.subList(2 + (b*6), 2 + (b*6) + 5);
            bingoBoards.add(new BingoBoard(boardInput));
        }
    }

    public Y2021D4Puzzle() {
        super();
    }

    @Override
    public Long solution1() {
        for (int s = 0; s < marks.size(); s++) {
            // List<Integer> subMarks = marks.subList(0, s);
            int mark = marks.get(s);

            for (int b = 0; b < this.bingoBoards.size(); b++) {
                BingoBoard board = this.bingoBoards.get(b);
                boolean boardHasWon = board.mark(mark);
                if (boardHasWon) {
                    // System.out.println("The winning board: \n" + board.toString());
                    return board.score();
                }
            }

        }

        return null;
    }

    @Override
    public Object solution2() {
        List<BingoBoard> winners = new ArrayList<BingoBoard>();

        for (int s = 0; s < marks.size(); s++) {
            // List<Integer> subMarks = marks.subList(0, s);
            int mark = marks.get(s);

            for (BingoBoard board : this.bingoBoards) {
                boolean boardHasWon = board.mark(mark);
                if (boardHasWon)
                    winners.add(board);
            }

            this.bingoBoards.removeAll(winners);
        }

        return winners.get(winners.size() - 1).score();
    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2021D4Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %s.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %s.", puzzle.solution2()));
    }

}