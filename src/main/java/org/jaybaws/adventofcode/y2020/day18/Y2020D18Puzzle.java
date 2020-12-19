package org.jaybaws.adventofcode.y2020.day18;
import org.jaybaws.adventofcode.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Y2020D18Puzzle extends BasePuzzle {

    public static final boolean TRACE = true;

    public Y2020D18Puzzle(List<String> altInput) {
        super(altInput);
    }

    @Override
    protected void prepare() {
        // @TODO!
    }

    public Y2020D18Puzzle() {
        super();
    }

    static final char add = '+';
    static final char multiply = '*';

    private static Long simpleCalculation(String s) {
        String[] operands = s.split("[*+]");
        String operators = s.replaceAll("\\d", "");

        long result = Long.valueOf(operands[0]);
        for (int i = 0; i < operators.length(); i++) {
            switch (operators.charAt(i)) {
                case add:
                    result += Long.valueOf(operands[i + 1]);
                    break;
                case multiply:
                    result *= Long.valueOf(operands[i + 1]);
                    break;
            }
        }

        return result;
    }

    public static Long calc1(String f) {
        String formula = f.replace(" ", "");
        Pattern p = Pattern.compile("\\([0123456789+*]*\\)");
        while (formula.contains("(")) {
            Matcher m = p.matcher(formula);
            StringBuffer sb = new StringBuffer();
            while (m.find())
                m.appendReplacement(sb, simpleCalculation(m.group().substring(1, m.group().length() - 1)).toString());
            m.appendTail(sb);
            formula = sb.toString();
        }
        return simpleCalculation(formula);
    }

    @Override
    public BigInteger solution1() {
        BigInteger sum = BigInteger.ZERO;
        for (String line : puzzleInput) {
            Long val = calc1(line);
            sum = sum.add(BigInteger.valueOf(val));
        }
        return sum;
    }

    public static String calc2(String ex){
        ex = ex.replaceAll("\\s", "");
        String[] c = ex.split("\\*");
        long prod = 1;
        for (String a : c) {
            String[] b = a.split("\\+");
            long sum = 0;
            for (String q : b) {
                sum += Long.parseLong(q);
            }
            prod *= sum;
        }
        return Long.toString(prod);
    }

    @Override
    public Long solution2() {
        long result = 0;

        for (String l : puzzleInput) {
            Stack<StringBuilder> a = new Stack<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < l.length(); i++){
                if (l.charAt(i) == '('){
                    a.push(sb);
                    sb = new StringBuilder();
                }
                else if (l.charAt(i) == ')'){
                    String ex = sb.toString();
                    String p = calc2(ex);
                    sb = a.pop();
                    sb.append(p);
                }
                else {
                    sb.append(l.charAt(i));
                }
            }
            String res = calc2(sb.toString());
            result += Long.parseLong(res);
        }

        return result;

    }

    public static void main(String[] args) {
        Puzzle puzzle = new Y2020D18Puzzle();

        System.out.println(String.format("\n\nQ1?\n--> Well, this: %d.", puzzle.solution1()));
        System.out.println(String.format("\n\nQ2?\n--> Well, this: %d.", puzzle.solution2()));

    }
}