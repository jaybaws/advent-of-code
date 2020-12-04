package org.jaybaws.adventofcode.y2019.day1;

public class Puzzle2 {

    static final long[] moduleMasses = { 119999, 91521, 89801, 130090, 102972, 105759, 70245, 95495, 73450, 90086, 138436, 52396, 115670, 113377, 125785, 97391, 124026, 69589, 129465, 86558, 84965, 142913, 113321, 61846, 53254, 105137, 146619, 105189, 98695, 142954, 105687, 82914, 112587, 92957, 114368, 70890, 89841, 90021, 125835, 119440, 97685, 74632, 94360, 113110, 118476, 122446, 64676, 54826, 107922, 147010, 127842, 96722, 144728, 128157, 85312, 105017, 99720, 54435, 102045, 76770, 116056, 60020, 59791, 79334, 144327, 133481, 83779, 105009, 101987, 77503, 70205, 84060, 143368, 82070, 94490, 78175, 109928, 116643, 107194, 147042, 138765, 54588, 97584, 95386, 106820, 71636, 115829, 133106, 77572, 142554, 131055, 122610, 53108, 130864, 71349, 114666, 56816, 86442, 146255, 145861 };

    public static long neededFuel(long mass) {
        if (mass <= 6) {
            return 0;
        } else {
            long neededFuel = (mass / 3 - 2);
            return neededFuel + neededFuel(neededFuel);
        }
    }

    public static void main(String[] args) {
        long totalNeededFuel = 0;

        for (long mass : moduleMasses) {
            totalNeededFuel += neededFuel(mass);
        }

        System.out.println(totalNeededFuel);
    }
}
