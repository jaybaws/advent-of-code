package org.jaybaws.adventofcode.y2019.day4;

public class Puzzle2 {

    public static boolean validate(long p) {

        char[] passwordChars = String.valueOf(p).toCharArray();
        String s = "";

        char previousChar = passwordChars[0]; // Set it to match the first char to avoid starting with a comma.
        for (char c : passwordChars) {
            if ((previousChar - 0) > (c - 0))
                return false;

            if (c != previousChar) {
                s += "," + c;
            } else {
                s += c;
            }
            previousChar = c;
        }

        for (String sub : s.split(",")) {
            if (sub.length() == 2)
                return true;
        }

        return false;
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