package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.stream.Collectors.toSet;

public class Day19 implements ParserSolver<Day19.Input, Long> {
    private static final Map<String, Long> CACHE = new HashMap<>();

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
    public Long solve(Day19.Input input) {
        return input.designs.stream()
                .filter(d -> isPossible(d, input.towels))
                .count();
    }

    private boolean isPossible(String design, Set<String> towels) {
        if (design.isEmpty()) {
            return true;
        }

        return towels.stream()
                .filter(design::startsWith)
                .map(t -> design.substring(t.length()))
                .anyMatch(m -> isPossible(m, towels));
    }

    private long howManyPossible(String design, Set<String> towels) {
        if (CACHE.containsKey(design)) {
            return CACHE.get(design);
        }

        if (design.isEmpty()) {
            return 1;
        }

        var result = towels.stream()
                .filter(design::startsWith)
                .map(t -> design.substring(t.length()))
                .map(t -> howManyPossible(t, towels))
                .reduce(0L, Long::sum);

        CACHE.put(design, result);

        return result;
    }

    @Override
    public Long solve2(Day19.Input input) {
        return input.designs.stream()
                .map(d -> howManyPossible(d, input.towels))
                .reduce(0L, Long::sum);
    }

    public record Input(Set<String> towels, Set<String> designs) {
    }
}
