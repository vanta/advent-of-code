package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

public class Day4 extends BaseDay<char[][], Integer> {
    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        var toBeRemoved = new ArrayList<>();

        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == '@') {
                    if (countNeighbors(parsedInput, i, j) < 4) {
                        toBeRemoved.add(Pair.of(i, j));
                    }
                }
            }
        }

        return toBeRemoved.size();
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;
    }

    private int countNeighbors(char[][] parsedInput, int i, int j) {
        int result = 0;

        if (i > 0 && parsedInput[i - 1][j] == '@') result++; // up
        if (i < parsedInput.length - 1 && parsedInput[i + 1][j] == '@') result++; // down
        if (j > 0 && parsedInput[i][j - 1] == '@') result++; // left
        if (j < parsedInput[i].length - 1 && parsedInput[i][j + 1] == '@') result++; // right
        if (i > 0 && j > 0 && parsedInput[i - 1][j - 1] == '@') result++; // up-left
        if (i > 0 && j < parsedInput[i].length - 1 && parsedInput[i - 1][j + 1] == '@') result++; // up-right
        if (i < parsedInput.length - 1 && j > 0 && parsedInput[i + 1][j - 1] == '@') result++; // down-left
        if (i < parsedInput.length - 1 && j < parsedInput[i].length - 1 && parsedInput[i + 1][j + 1] == '@')
            result++; // down-right

        return result;
    }

}
