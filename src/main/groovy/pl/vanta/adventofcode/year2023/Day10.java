package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.stream.Stream;

public class Day10 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {


        return 1111;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {

        return 1111;
    }
}
