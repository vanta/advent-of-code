package pl.vanta.adventofcode.year2024;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Long.*;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.Map.Entry;
import static java.util.Map.entry;
import static java.util.stream.Collectors.toMap;

public class Day7 implements ParserSolver<Map<Long, List<Integer>>, Long> {

    @Override
    public int getDayNumber() {
        return 7;
    }

    @Override
    public Map<Long, List<Integer>> parse(String lines) {
        return stream(lines.split("\n"))
                .map(l -> l.split(":"))
                .map(a -> entry(parseLong(a[0].trim()), numbersToList(a[1])))
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

    private static List<Integer> numbersToList(String s) {
        return stream(s.trim().split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public Long solve(Map<Long, List<Integer>> parsedInput) {
        return parsedInput.entrySet().stream()
                .filter(e -> isValid(e.getKey(), e.getValue()))
                .map(Entry::getKey)
                .reduce(0L, Long::sum);
    }

    private boolean isValid(long expected, List<Integer> values) {
        Set<Long> results = new HashSet<>();
        results.add(values.getFirst().longValue());

        for (int i = 1; i < values.size(); i++) {
            Set<Long> newResults = new HashSet<>();
            for (Long result : results) {
                newResults.add(result + values.get(i));
                newResults.add(result * values.get(i));
            }
            results = newResults;
        }

        return results.contains(expected);
    }

    private boolean isValid2(long expected, List<Integer> values) {
        Set<Long> results = new HashSet<>();
        results.add(values.getFirst().longValue());

        for (int i = 1; i < values.size(); i++) {
            Set<Long> newResults = new HashSet<>();
            for (Long result : results) {
                var value = values.get(i);
                newResults.add(result + value);
                newResults.add(result * value);
                newResults.add(parseLong("" + result + value));
            }
            results = newResults;
        }

        return results.contains(expected);
    }

    @Override
    public Long solve2(Map<Long, List<Integer>> parsedInput) {
        return parsedInput.entrySet().stream()
                .filter(e -> isValid2(e.getKey(), e.getValue()))
                .map(Entry::getKey)
                .reduce(0L, Long::sum);

    }

}
