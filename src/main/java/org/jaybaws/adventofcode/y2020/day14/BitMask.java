package org.jaybaws.adventofcode.y2020.day14;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BitMask {

    static final boolean TRACE = false;

    public static String toBinary(BigInteger val, int length) {
        String result = "";
        for (int i = length - 1; i >= 0; i--) {
            result += val.testBit(i) ? "1":"0";
        }
        return result;
    }

    public static BigInteger apply(String mask, BigInteger val) {
        BigInteger output = val.add(BigInteger.ZERO);

        char[] maskElements = mask.toCharArray();

        for (int i = 35; i >= 0; i--) {
            int pos = 35 - i;
            switch (maskElements[i]) {
                case '1':
                    if (TRACE)
                        System.out.println(String.format("Setting bit %d!", pos));
                    output = output.setBit(pos);
                    break;
                case '0':
                    if (TRACE)
                        System.out.println(String.format("Clearing bit %d!", pos));
                    output = output.clearBit(pos);
                    break;
            }
        }

        return output;
    }



    public static List<BigInteger> determineAddresses(String mask, BigInteger address) {
        String fixedMask = mask.replaceAll("X", "F").replaceAll("0", "X");
        List<String> fixedMaskList = new ArrayList<String>();
        fixedMaskList.add(fixedMask);

        List<String> masks = fixedMaskList;
        boolean proceed = true;
        while (proceed) {
            proceed = false;
            List<String> seen = new ArrayList<String>();
            List<String> updates = new ArrayList<String>();
            for (String m : masks) {
                if (m.contains("F")) {
                    updates.add(m.replaceFirst("F", "0"));
                    updates.add(m.replaceFirst("F", "1"));
                    seen.add(m);
                    proceed = true;
                }
            }
            masks.removeAll(seen);
            masks.addAll(updates);
        }

        List<BigInteger> addresses = new ArrayList<BigInteger>();
        for (String m : masks) {
            addresses.add(apply(m, address));
        }

        return addresses;
    }

}