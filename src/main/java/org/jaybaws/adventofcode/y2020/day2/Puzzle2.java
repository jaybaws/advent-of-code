package org.jaybaws.adventofcode.y2020.day2;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Puzzle2 {

    private static final boolean TRACE = false;

    public static int occurrences(char c, String s) {
        return StringUtils.countMatches(s, c);
    }

    public static boolean isValid(String line) {
        String items[] = line.split(" ");
        String counts[] = items[0].split("-");

        int pos1 = Integer.valueOf(counts[0]);
        int pos2 = Integer.valueOf(counts[1]);

        char c = items[1].charAt(0);

        String s = items[2].substring(pos1 -1, pos1) + items[2].substring(pos2 -1, pos2);

        if (TRACE) System.out.println(
                String.format("Looking for exactly one occurrence of '%c' in '%s'.", c, s)
        );

        int o = occurrences(c, s);

        return (o == 1);
    }

    public static void main(String[] args) {
         try (InputStreamReader streamReader =new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("input_2020_2.txt"), StandardCharsets.UTF_8);
              BufferedReader reader = new BufferedReader(streamReader)) {

             int validCount = 0;
             String line;
             while ((line = reader.readLine()) != null) {
                 if (isValid(line)) {
                     validCount++;
                 }
             }

            System.out.println("Total valid passwords: " + validCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}