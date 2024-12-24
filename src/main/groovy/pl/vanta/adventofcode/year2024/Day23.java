package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

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
        var map = new HashMap<String, Set<String>>();

        input.stream()
                .map(s -> s.split("-"))
                .forEach(s -> {
                    map.computeIfAbsent(s[0], k -> new HashSet<>());
                    map.computeIfAbsent(s[1], k -> new HashSet<>());

                    map.get(s[0]).add(s[1]);
                    map.get(s[1]).add(s[0]);
                });

        var cycles = findCycles(map);

        return (int) cycles.stream()
                .filter(l -> l.stream().anyMatch(s -> s.startsWith("t")))
                .count();
    }

    private static Set<Set<String>> findCycles(Map<String, Set<String>> map) {
        var result = new HashSet<Set<String>>();

        map.forEach((k, values) -> {
            for (String v : values) {
                var cycle = findCycleInternal(k, v, values, map);
                if (!cycle.isEmpty()) {
                    result.addAll(cycle);
                }
            }
        });

        return result;
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
