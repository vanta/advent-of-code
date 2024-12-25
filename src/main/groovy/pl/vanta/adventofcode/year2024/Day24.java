package pl.vanta.adventofcode.year2024;

import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;

public class Day24 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 24;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        return 1;
    }

    @Override
    public Integer solve2(List<String> input) {
        return 1;
    }

}
