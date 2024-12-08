package pl.vanta.adventofcode.year2024;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;

public class Day8 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public char[][] parse(String lines) {
        return stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {


        return 0;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;

    }

}
