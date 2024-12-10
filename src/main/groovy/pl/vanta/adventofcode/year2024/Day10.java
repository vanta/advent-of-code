package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Collections.swap;

public class Day10 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 10;
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
