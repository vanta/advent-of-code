package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Math.floorDiv;
import static java.util.Arrays.stream;
import static java.util.Collections.indexOfSubList;
import static java.util.Map.Entry;
import static java.util.Map.entry;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.iterate;

public class Day22 implements ParserSolver<List<Integer>, Long> {

    @Override
    public int getDayNumber() {
        return 22;
    }

    @Override
    public List<Integer> parse(String lines) {
        return stream(lines.split("\n"))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public Long solve(List<Integer> input) {
        return input.stream()
                .map(i -> secret(i, 2000))
                .reduce(0L, Long::sum);
    }

    private long secret(long initialSecret, int howMany) {
        return iterate(initialSecret, this::nextSecret)
                .limit(howMany + 1)
                .reduce(0L, (l1, l2) -> l2);
    }

    @Override
    public Long solve2(List<Integer> input) {
        return (long) input.stream()
                .map(i -> secret2(i, 2000))
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(groupingBy(Entry::getKey, summingInt(Entry::getValue)))
                .values().stream()
                .max(Integer::compareTo)
                .orElseThrow();
    }

    private Map<String, Integer> secret2(long initialSecret, int howMany) {
        var digits = iterate(initialSecret, this::nextSecret)
                .limit(howMany + 1)
                .map(l -> (int) (l % 10))
                .toList();

        var diffs = iterate(1, i -> i + 1)
                .limit(digits.size() - 1)
                .map(i -> digits.get(i) - digits.get(i - 1))
                .toList();

        var aa = windows(diffs, 4).stream()
                .distinct()
                .map(w -> entry(w, indexOfSubList(diffs, w)))
                .filter(e -> e.getValue() != -1)
                .collect(toMap(
                        e -> e.getKey().toString(),
                        e -> digits.get(e.getValue() + 4)
                ));
        return aa;
    }

    public static List<List<Integer>> windows(List<Integer> list, int windowSize) {
        if (list.size() < windowSize) {
            return List.of(); //
        }

        return range(0, list.size() - windowSize + 1)
                .mapToObj(start -> list.subList(start, start + windowSize))
                .toList();
    }

    private long nextSecret(long secret) {
        var nextSecret = secret;

        nextSecret = prune(mix(nextSecret, nextSecret * 64));
        nextSecret = prune(mix(nextSecret, floorDiv(nextSecret, 32)));
        nextSecret = prune(mix(nextSecret, nextSecret * 2048));

        return nextSecret;
    }

    private long mix(long secret, long value) {
        return secret ^ value;
    }

    private long prune(long secret) {
        return secret % 16777216;
    }
}
