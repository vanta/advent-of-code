package pl.vanta.adventofcode.year2025;

import java.util.stream.Stream;

public class Day5 extends BaseDay<char[][], Integer> {
    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
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
