package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.stream.Stream;

public class Day15 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 15;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.trim().split(","))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return parsedInput.stream()
                .peek(System.out::println)
                .map(Day15::hash)
                .peek(System.out::println)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return -1;
    }

    private static int hash(String s) {
        return s.chars()
                .reduce(0, (left, right) -> ((left + right) * 17) % 256);
    }
}
