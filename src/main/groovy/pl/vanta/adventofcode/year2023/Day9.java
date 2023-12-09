package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

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
        return parsedInput.stream()
                .map(this::getNextNumber)
                .reduce(0, Integer::sum);
    }

    private int getNextNumber(int[] ints) {
        if (stream(ints).allMatch(i -> i == 0)) {
            return 0;
        }

        var tmp = new int[ints.length - 1];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = ints[i + 1] - ints[i];
        }

        return ints[ints.length - 1] + getNextNumber(tmp);
    }

    @Override
    public Integer solve2(List<int[]> parsedInput) {
        return 0;
    }

}
