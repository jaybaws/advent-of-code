package org.jaybaws.adventofcode.y2019.day4;

public class Puzzle1 {

    public static boolean validate(long p) {

        byte[] passwordBytes = String.valueOf(p).getBytes();
        for (int i = 0; i < passwordBytes.length - 1; i++) {
            if (passwordBytes[i + 1]  < passwordBytes[i])
                return false;
        }

        boolean hasAdjacentEqual = false;
        for (int i = 0; i < passwordBytes.length - 1; i++) {
            if (passwordBytes[i + 1]  == passwordBytes[i])
                hasAdjacentEqual = true;
        }

        return hasAdjacentEqual;
    }

    public static void main(String[] args) {

        long matches = 0;
        for (long password = 256310; password <= 732736; password++) {
            if (validate(password))
                matches++;
        }

        System.out.println(matches);
    }
}
