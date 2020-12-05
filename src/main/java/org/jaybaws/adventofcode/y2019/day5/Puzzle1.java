package org.jaybaws.adventofcode.y2019.day5;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Puzzle1 {

    static final boolean TRACE = false;

    // Taken/adapted from: https://adventofcode.com/2019/day/5/input
    static final int[] puzzle_intcodes = { 3,225,1,225,6,6,1100,1,238,225,104,0,1102,17,65,225,102,21,95,224,1001,224,-1869,224,4,224,1002,223,8,223,101,7,224,224,1,224,223,223,101,43,14,224,1001,224,-108,224,4,224,102,8,223,223,101,2,224,224,1,223,224,223,1101,57,94,225,1101,57,67,225,1,217,66,224,101,-141,224,224,4,224,102,8,223,223,1001,224,1,224,1,224,223,223,1102,64,34,225,1101,89,59,225,1102,58,94,225,1002,125,27,224,101,-2106,224,224,4,224,102,8,223,223,1001,224,5,224,1,224,223,223,1102,78,65,225,1001,91,63,224,101,-127,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,1102,7,19,224,1001,224,-133,224,4,224,102,8,223,223,101,6,224,224,1,224,223,223,2,61,100,224,101,-5358,224,224,4,224,102,8,223,223,101,3,224,224,1,224,223,223,1101,19,55,224,101,-74,224,224,4,224,102,8,223,223,1001,224,1,224,1,224,223,223,1101,74,68,225,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,107,677,677,224,102,2,223,223,1006,224,329,1001,223,1,223,1008,226,677,224,102,2,223,223,1006,224,344,1001,223,1,223,7,226,677,224,102,2,223,223,1005,224,359,1001,223,1,223,8,226,226,224,102,2,223,223,1006,224,374,1001,223,1,223,1007,226,226,224,102,2,223,223,1006,224,389,101,1,223,223,8,677,226,224,1002,223,2,223,1005,224,404,101,1,223,223,1108,677,226,224,102,2,223,223,1006,224,419,1001,223,1,223,1108,226,677,224,102,2,223,223,1006,224,434,101,1,223,223,1108,677,677,224,1002,223,2,223,1005,224,449,101,1,223,223,1008,677,677,224,1002,223,2,223,1006,224,464,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,479,101,1,223,223,108,677,677,224,1002,223,2,223,1005,224,494,101,1,223,223,107,226,677,224,1002,223,2,223,1006,224,509,101,1,223,223,107,226,226,224,102,2,223,223,1006,224,524,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,554,1001,223,1,223,8,226,677,224,1002,223,2,223,1006,224,569,101,1,223,223,1007,677,677,224,102,2,223,223,1005,224,584,1001,223,1,223,1107,677,226,224,1002,223,2,223,1006,224,599,101,1,223,223,7,226,226,224,1002,223,2,223,1005,224,614,101,1,223,223,108,677,226,224,1002,223,2,223,1005,224,629,1001,223,1,223,108,226,226,224,1002,223,2,223,1005,224,644,101,1,223,223,1007,677,226,224,1002,223,2,223,1006,224,659,101,1,223,223,1107,226,226,224,102,2,223,223,1005,224,674,1001,223,1,223,4,223,99,226 };
    static final int[] sample_intcodes = { 1002,4,3,4,33 };

    static int[] intcodes = puzzle_intcodes;

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    static int getValue(int[] dataset, int position, char paramMode) {
        if (paramMode == '1') {
            return position;
        } else {
            return dataset[position];
        }
    }

    static int handle(int[] dataset, int position) {
        String opCodeStr = padLeftZeros(Integer.toString(dataset[position]), 5);

        String de = opCodeStr.substring(3);
        char c = opCodeStr.substring(2, 3).charAt(0);
        char b = opCodeStr.substring(1, 2).charAt(0);
        char a = opCodeStr.substring(0, 1).charAt(0);

        switch (de) {
            case "01":
                return handle_add(dataset, getValue(dataset, dataset[position + 1], c), getValue(dataset, dataset[position + 2], b), dataset[position + 3]);
            case "02":
                return handle_multiply(dataset, getValue(dataset, dataset[position + 1], c), getValue(dataset, dataset[position + 2], b), dataset[position + 3]);
            case "03":
                return handle_readInt(dataset, dataset[position + 1]);
            case "04":
                return handle_outputInt(dataset, dataset[position + 1]);
        }

        return 1; // Gotta assume something. This will blow up in your face!
    }

    static int handle_add(int[] dataset, int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[ADD] %d + %d, store @ %d", val1, val2, resultPos));
        dataset[resultPos] = val1 + val2;

        return 4; // advance 4 steps.
    }

    static int handle_multiply(int[] dataset, int val1, int val2, int resultPos) {
        if (TRACE)
            System.out.println(String.format("[MULTIPLY] %d * %d, store @ %d", val1, val2, resultPos));
        dataset[resultPos] = val1 * val2;

        return 4; // advance 4 steps.
    }

    static int handle_readInt(int[] dataset, int resultPos) {
        System.out.println("PROVIDING VALUE '1' AS INPUT!");
        int value = 1; // Integer.valueOf(System.console().readLine());
        dataset[resultPos] = value;

        return 2; // advance 2 steps.
    }

    static int handle_outputInt(int[] dataset, int dataPos1) {
        System.out.println(dataset[dataPos1]);
        return 2; // advance 2 steps.
    }

    public static void main(String[] args) {
        for (int i = 0; intcodes[i] != 99; ) {
            i += handle(intcodes, i);
        }

        if (TRACE)
            System.out.println(
                    IntStream.of(intcodes)
                            .mapToObj(Integer::toString)
                            .collect(Collectors.joining(", "))
            );
    }
}