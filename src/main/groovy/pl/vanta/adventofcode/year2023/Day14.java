package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day14 implements ParserSolver<char[][], Integer> {
    public static final int CYCLES = 1_000_000_000;

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
        return count(tiltNorth(parsedInput));
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return count(tilt(parsedInput, CYCLES));
    }

    private char[][] tilt(char[][] parsedInput, int tilts) {
        Map<String, Integer> cache = new HashMap<>();

        for (int i = 0; i < tilts; i++) {
            String key = toString(parsedInput);
            if (cache.containsKey(key)) {
                int cycle = i - cache.get(key);
                int remaining = tilts - i - 1;
                int cycles = remaining / cycle;
                i += (cycles * cycle);
            } else {
                cache.put(key, i);
            }

//            var north = tiltNorth(parsedInput);
//            var west = tiltNorth(transpose(north));
//            var south = tiltNorth(transpose(west));
//            var east = tiltNorth(transpose(south));
            var north = tiltNorth(parsedInput);
            var west = tiltWest(north);
            var south = tiltSouth(west);
            var east = tiltEast(south);

            parsedInput = east;
        }

        return parsedInput;
    }

    private String toString(char[][] input) {
        return Stream.of(input)
                .map(String::new)
                .reduce("", (s1, s2) -> s1 + s2);
    }

    private char[][] tiltNorth(char[][] parsedInput) {
        for (int i = 1; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'O') {
                    swap(parsedInput, findTiltLimitNorth(parsedInput, i, j), j, i, j);
                }
            }
        }

        return parsedInput;
    }

    private char[][] tiltSouth(char[][] parsedInput) {
        for (int i = parsedInput.length - 2; i >= 0; i--) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'O') {
                    swap(parsedInput, findTiltLimitSouth(parsedInput, i, j), j, i, j);
                }
            }
        }

        return parsedInput;
    }

    private char[][] tiltWest(char[][] parsedInput) {
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 1; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'O') {
                    swap(parsedInput, i, findTiltLimitWest(parsedInput, i, j), i, j);
                }
            }
        }

        return parsedInput;
    }

    private char[][] tiltEast(char[][] parsedInput) {
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = parsedInput[i].length - 2; j >= 0; j--) {
                if (parsedInput[i][j] == 'O') {
                    swap(parsedInput, i, findTiltLimitEast(parsedInput, i, j), i, j);
                }
            }
        }

        return parsedInput;
    }

    private int findTiltLimitNorth(char[][] parsedInput, int i, int j) {
        for (int k = i - 1; k >= 0; k--) {
            if (parsedInput[k][j] != '.') {
                return k + 1;
            }
        }

        return 0;
    }

    private int findTiltLimitSouth(char[][] parsedInput, int i, int j) {
        for (int k = i + 1; k < parsedInput.length; k++) {
            if (parsedInput[k][j] != '.') {
                return k - 1;
            }
        }

        return parsedInput.length - 1;
    }

    private int findTiltLimitWest(char[][] parsedInput, int i, int j) {
        for (int k = j - 1; k >= 0; k--) {
            if (parsedInput[i][k] != '.') {
                return k + 1;
            }
        }

        return 0;
    }

    private int findTiltLimitEast(char[][] parsedInput, int i, int j) {
        for (int k = j + 1; k < parsedInput[0].length; k++) {
            if (parsedInput[i][k] != '.') {
                return k - 1;
            }
        }

        return parsedInput[0].length - 1;
    }

    private static void swap(char[][] parsedInput, int row1, int col1, int row2, int col2) {
        if (row1 == row2 && col1 == col2) {
            return;
        }
        var tmp = parsedInput[row1][col1];
        parsedInput[row1][col1] = parsedInput[row2][col2];
        parsedInput[row2][col2] = tmp;
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

    private void print(char[][]... groups) {
        for (int i = 0; i < groups[0].length; i++) { //rows
            for (char[][] group : groups) { //groups
                for (char c : group[i]) { //cols
                    System.out.print(c);
                }
                System.out.print('\t');
            }
            System.out.println();
        }
    }

    private char[][] transpose(char[][] input) {
        var result = new char[input[0].length][input.length];

        for (int i = 0; i < input.length; i++) { //rows
            for (int j = 0; j < input[i].length; j++) { //cols
                result[j][i] = input[i][j];
            }
        }

        return result;
    }
}
