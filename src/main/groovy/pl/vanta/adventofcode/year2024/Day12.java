package pl.vanta.adventofcode.year2024;

import java.util.Arrays;

import pl.vanta.adventofcode.ParserSolver;

public class Day12 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public char[][] parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return -1;
    }

}
