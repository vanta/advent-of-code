package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.stream.Stream;

public class Day14 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 14;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return count(load(parsedInput));
    }

    private char[][] load(char[][] parsedInput) {
        for(int i = 1; i < parsedInput.length; i++) {
            for(int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'O') {
                    int row = findRowToLoad(parsedInput, i, j);

                    var tmp = parsedInput[row][j];
                    parsedInput[row][j] = 'O';
                    parsedInput[i][j] = tmp;
                }
            }
        }

        print(parsedInput);

        return parsedInput;
    }

    private int findRowToLoad(char[][] parsedInput, int i, int j) {
        for (int k = i - 1; k >= 0; k--) {
            if(parsedInput[k][j] != '.') {
                return k + 1;
            }
        }

        return 0;
    }

    private int count(char[][] loaded) {
        int counter = 0;
        for (int i = 0; i < loaded.length; i++) {
            for (int j = 0; j < loaded[i].length; j++) {
                if (loaded[i][j] == 'O') {
                    counter += (loaded.length - i);
                }
            }
        }

        return counter;
    }

    void print(char[][] parsedInput) {
        for (char[] chars : parsedInput) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return -1;
    }

}
