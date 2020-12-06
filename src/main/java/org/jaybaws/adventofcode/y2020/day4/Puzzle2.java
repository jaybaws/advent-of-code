package org.jaybaws.adventofcode.y2020.day4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Deprecated
public class Puzzle2 {

    static final String[] requiredFields = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };

    enum FIELDS { byr, iyr, eyr, hgt, hcl, ecl, pid };

    enum ECL { amb, blu, brn, gry, grn, hzl, oth };

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
        Properties props = parsePropertiesString(password.replace(":", "=").replace(" ", "\n"));

        for (String k : requiredFields) {
            if (!props.containsKey(k))
                return false;
        }

        for (Object k: props.keySet()) {
            String key = (String) k;
            String value = (String) props.get(k);

            boolean isValid = true;
            switch (key) {
                case "byr":
                    isValid = validateBYR(value);
                    break;
                case "iyr":
                    isValid = validateIYR(value);
                    break;
                case "eyr":
                    isValid = validateEYR(value);
                    break;
                case "hgt":
                    isValid = validateHGT(value);
                    break;
                case "hcl":
                    isValid = validateHCL(value);
                    break;
                case "ecl":
                    isValid = validateECL(value);
                    break;
                case "pid":
                    isValid = validatePID(value);
                    break;
            }

            if (!isValid)
                return false;

        }

        return true;
    }

    public static boolean validateBYR(String v) {
        int byr = Integer.valueOf(v);
        return (byr >= 1920 && byr <= 2002);
    }

    public static boolean validateIYR(String v) {
        int iyr = Integer.valueOf(v);
        return (iyr >= 2010 && iyr <= 2020);
    }

    public static boolean validateEYR(String v) {
        int eyr = Integer.valueOf(v);
        return (eyr >= 2020 && eyr <= 2030);
    }

    public static boolean validateHCL(String v) {
        return v.matches("^\\#[0-9a-f]{6}$");
    }

    public static boolean validateECL(String v) {
        try {
            return (ECL.valueOf(v) != null);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean validatePID(String v) {
        return v.matches("^\\d{9}$");
    }

    public static boolean validateHGT(String v) {
        if (v.endsWith("in")) {
            int inches = Integer.valueOf(v.substring(0, v.length() - 2));
            return (inches >= 59 && inches <= 76);
        } else if (v.endsWith("cm")) {
            int cms = Integer.valueOf(v.substring(0, v.length() - 2));
            return (cms >= 150 && cms <= 193);
        } else
            return false;
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
