package pl.vanta.adventofcode.year2024;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

public class Day23 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 23;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        var map = buildMap(input);

        return (int) findCycles(map).stream()
                .filter(l -> l.stream().anyMatch(s -> s.startsWith("t")))
                .count();
    }

    private static Map<String, Set<String>> buildMap(List<String> input) {
        var map = new HashMap<String, Set<String>>();

        input.stream()
                .map(s -> s.split("-"))
                .forEach(s -> {
                    map.computeIfAbsent(s[0], k -> new HashSet<>());
                    map.computeIfAbsent(s[1], k -> new HashSet<>());

                    map.get(s[0]).add(s[1]);
                    map.get(s[1]).add(s[0]);
                });

        return map;
    }

    private static Set<Set<String>> findCycles(Map<String, Set<String>> map) {
        return map.entrySet().stream()
                .map(e -> getCycles(map, e))
                .flatMap(Set::stream)
                .collect(toSet());
    }

    private static Set<Set<String>> getCycles(Map<String, Set<String>> map, Map.Entry<String, Set<String>> e) {
        return e.getValue().stream()
                .map(v -> findCycleInternal(e.getKey(), v, e.getValue(), map))
                .flatMap(Set::stream)
                .filter(not(Set::isEmpty))
                .collect(toSet());
    }

    private static Set<Set<String>> findCycleInternal(String current, String neighbour, Set<String> values, Map<String, Set<String>> map) {
        return map.get(neighbour).stream()
                .filter(values::contains)
                .map(s -> Set.of(current, neighbour, s))
                .collect(toSet());
    }

    @Override
    public Integer solve2(List<String> input) {
        return -1;
    }

}
