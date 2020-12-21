package org.jaybaws.adventofcode.y2020.day20;

public class Calypso {

    // Search for ze zecrets of ze ocean!
    public static char[][] mark(char[][] image) {
        char[][] newImage = image;
        for (int r = 0; r < newImage.length; r++) {
            for (int c = 0; c < newImage[0].length; c++) {
                if (c + 19 < newImage[0].length && r + 2 < newImage.length)
                    if (
                            newImage[r][c + 18] == '#' &&
                            newImage[r + 1][c] == '#' &&
                            newImage[r + 1][c + 5] == '#' &&
                            newImage[r + 1][c + 6] == '#' &&
                            newImage[r + 1][c + 11] == '#' &&
                            newImage[r + 1][c + 12] == '#' &&
                            newImage[r + 1][c + 17] == '#' &&
                            newImage[r + 1][c + 18] == '#' &&
                            newImage[r + 1][c + 19] == '#' &&
                            newImage[r + 2][c + 1] == '#' &&
                            newImage[r + 2][c + 4] == '#' &&
                            newImage[r + 2][c + 7] == '#' &&
                            newImage[r + 2][c + 10] == '#' &&
                            newImage[r + 2][c + 13] == '#' &&
                            newImage[r + 2][c + 16] == '#'
                    ) {
                        newImage[r][c + 18] = 'O';
                        newImage[r + 1][c] = 'O';
                        newImage[r + 1][c + 5] = 'O';
                        newImage[r + 1][c + 6] = 'O';
                        newImage[r + 1][c + 11] = 'O';
                        newImage[r + 1][c + 12] = 'O';
                        newImage[r + 1][c + 17] = 'O';
                        newImage[r + 1][c + 18] = 'O';
                        newImage[r + 1][c + 19] = 'O';
                        newImage[r + 2][c + 1] = 'O';
                        newImage[r + 2][c + 4] = 'O';
                        newImage[r + 2][c + 7] = 'O';
                        newImage[r + 2][c + 10] = 'O';
                        newImage[r + 2][c + 13] = 'O';
                        newImage[r + 2][c + 16] = 'O';
                    }
            }
        }

        return newImage;
    }

}