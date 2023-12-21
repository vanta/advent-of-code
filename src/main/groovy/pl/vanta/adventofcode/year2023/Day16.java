package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.stream.Stream;

public class Day16 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 16;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.trim().split(","))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return -1;
    }
}
