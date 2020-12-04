package org.jaybaws.adventofcode.y2019.day2;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle1 {

    static final boolean TRACE = false;

    // Taken (and modified!) from: https://adventofcode.com/2019/day/2/input
    static final int[] puzzle_intcodes = { 1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,19,5,23,2,6,23,27,1,6,27,31,2,31,9,35,1,35,6,39,1,10,39,43,2,9,43,47,1,5,47,51,2,51,6,55,1,5,55,59,2,13,59,63,1,63,5,67,2,67,13,71,1,71,9,75,1,75,6,79,2,79,6,83,1,83,5,87,2,87,9,91,2,9,91,95,1,5,95,99,2,99,13,103,1,103,5,107,1,2,107,111,1,111,5,0,99,2,14,0,0 };

    // Taken from: https://adventofcode.com/2019/day/2/
    static final int[] sample_intcodes_1 = { 1,9,10,3,2,3,11,0,99,30,40,50 };
    static final int[] sample_intcodes_2 = { 1,0,0,0,99 };
    static final int[] sample_intcodes_3 = { 2,3,0,3,99 };
    static final int[] sample_intcodes_4 = { 2,4,4,5,99,0 };
    static final int[] sample_intcodes_5 = { 1,1,1,4,99,5,6,0,99 };

    static int[] intcodes = puzzle_intcodes;

    static void handle(int[] dataset, int opCode, int dataPos1, int dataPos2, int resultPos) {
        if (opCode == 1) {
            handle_add(dataset, dataPos1, dataPos2, resultPos);
        } else if (opCode == 2) {
            handle_multiply(dataset, dataPos1, dataPos2, resultPos);
        }
    }

    static void handle_add(int[] dataset, int dataPos1, int dataPos2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[ADD] pos(%d) + pos(%d), store @ %d", dataPos1, dataPos2, resultPos));
        dataset[resultPos] = dataset[dataPos1] + dataset[dataPos2];
    }

    static void handle_multiply(int[] dataset, int dataPos1, int dataPos2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[MULTIPLY] pos(%d) * pos(%d), store @ %d", dataPos1, dataPos2, resultPos));
        dataset[resultPos] = dataset[dataPos1] * dataset[dataPos2];
    }


    public static void main(String[] args) {
        for (int i = 0; intcodes[i] != 99; i = i + 4) {
            handle(intcodes, intcodes[i], intcodes[i+1], intcodes[i+2],intcodes[i+3]);
        }

        System.out.println(
            IntStream.of(intcodes)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(", "))
        );
    }
}