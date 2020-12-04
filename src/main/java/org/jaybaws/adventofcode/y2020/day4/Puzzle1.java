package org.jaybaws.adventofcode.y2020.day4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Puzzle1 {

    static final String[] requiredFields = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

    public static String loadResource(String name) {
        String result = "";
        try (InputStreamReader streamReader =new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(name), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    result += "\n";
                } else {
                    result += line + " ";
                }
            }

        } catch (IOException e) {
            return null;
        }

        return result;
    }

    public static Properties parsePropertiesString(String s) {
        final Properties p = new Properties();
        try {
            p.load(new StringReader(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static boolean isValidPassword(String password) {
        Properties props = parsePropertiesString(password.replace(":", "= ").replace(" ", "\n"));

        for (String k : requiredFields) {
            if (!props.containsKey(k))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String[] passwords = loadResource("input_2020_4.txt").split("\n");

        int validCount = 0;
        for (String p : passwords) {
            if (isValidPassword(p))
                validCount++;
        }

        System.out.println(validCount);
    }
}