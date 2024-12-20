package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.stream.Collectors.toSet;

public class Day19 implements ParserSolver<Day19.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 19;
    }

    @Override
    public Day19.Input parse(String lines) {
        var allLines = Arrays.stream(lines.split("\n"))
                .toList();

        var towels = Arrays.stream(allLines.getFirst().split(", "))
                .collect(toSet());

        return new Input(towels, new HashSet<>(allLines.subList(2, allLines.size())));
    }

    @Override
    public Integer solve(Day19.Input input) {
        return (int) input.designs.stream()
                .filter(d -> isPossible(d, input.towels))
                .count();
    }

    private boolean isPossible(String design, Set<String> towels) {
        if (design.isEmpty()) {
            return true;
        }

        return towels.stream()
                .filter(design::startsWith)
                .anyMatch(m -> isPossible(design.replace(m, ""), towels));
    }

    @Override
    public Integer solve2(Day19.Input input) {
        return 0;
    }

    public record Input(Set<String> towels, Set<String> designs) {
    }
}
