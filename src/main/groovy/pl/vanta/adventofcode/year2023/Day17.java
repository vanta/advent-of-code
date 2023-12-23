package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.stream.Stream;

public class Day17 implements ParserSolver<int[][], Integer> {

    @Override
    public int getDayNumber() {
        return 17;
    }

    @Override
    public int[][] parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(this::toIntArray)
                .toArray(int[][]::new);
    }

    private int[] toIntArray(String s) {
        return s.chars()
                .map(c -> c - 48)
                .toArray();
    }

    @Override
    public Integer solve(int[][] parsedInput) {


        return 0;
    }

    @Override
    public Integer solve2(int[][] parsedInput) {
        return 0;
    }

}
