package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;

public class Day21 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 19;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {

        return 0;
    }

    @Override
    public Integer solve2(List<String> input) {

        return 0;
    }

    public record Input(Set<String> towels, Set<String> designs) {
    }
}
