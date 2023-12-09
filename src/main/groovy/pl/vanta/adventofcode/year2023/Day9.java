package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day9 implements ParserSolver<List<int[]>, Integer> {

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public List<int[]> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day9::parseLine)
                .toList();
    }

    private static int[] parseLine(String s) {
        return new Scanner(s).tokens()
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    @Override
    public Integer solve(List<int[]> parsedInput) {


        return 0;
    }

    @Override
    public Integer solve2(List<int[]> parsedInput) {
        return 0;
    }

}
