package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class Day21 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 21;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        return input.stream()
                .map(s -> complexity(s) * parseInt(s.substring(0, s.length() - 1)))
                .reduce(0, Integer::sum);
    }

    private int complexity(String s) {
        return 1;
    }

    @Override
    public Integer solve2(List<String> input) {

        return 0;
    }

    public record Input(Set<String> towels, Set<String> designs) {
    }
}
