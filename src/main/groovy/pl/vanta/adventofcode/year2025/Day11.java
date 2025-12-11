package pl.vanta.adventofcode.year2025;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class Day11 extends BaseDay<List<Day11.Input>, Integer> {
    @Override
    public int getDayNumber() {
        return 11;
    }

    @Override
    public List<Day11.Input> parse(String input) {
        return stream(input.split("\n"))
                .map(Input::parse)
                .toList();
    }

    @Override
    public Integer solve(List<Day11.Input> parsedInput) {
        var map = parsedInput.stream()
                .collect(toMap(Input::label, Input::outputs));

        return stepBack(map, "out", new ConcurrentHashMap<>());
    }

    private static int stepBack(Map<String, Set<String>> map, String current, Map<String, Integer> cache) {
        if ("you".equals(current)) {
            return 1;
        }

        if (!cache.containsKey(current)) {
            var temp = map.entrySet().stream()
                    .filter(e -> e.getValue().contains(current))
                    .map(Map.Entry::getKey)
                    .mapToInt(k -> stepBack(map, k, cache))
                    .reduce(0, Integer::sum);

            cache.put(current, temp);
        }

        return cache.get(current);
    }

    @Override
    public Integer solve2(List<Day11.Input> parsedInput) {


        return 11;
    }

    public record Input(String label, Set<String> outputs) {
        public static Input parse(String s) {
            var a = s.split(": ");

            return new Input(a[0], Set.of(a[1].split(" ")));
        }
    }

    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        Map<T, R> cache = new HashMap<>();
        return input -> cache.computeIfAbsent(input, function);
    }
}
