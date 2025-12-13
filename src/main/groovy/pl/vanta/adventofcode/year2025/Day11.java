package pl.vanta.adventofcode.year2025;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.join;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public class Day11 extends BaseDay<List<Day11.Input>, Long> {

    private static final String STOP1 = "you";
    private static final String STOP2 = "out";

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
    public Long solve(List<Day11.Input> parsedInput) {
        var map = parsedInput.stream()
                .collect(toMap(Input::label, Input::outputs));

        return (long) stepBack(map, new ConcurrentHashMap<>(), "out");
    }

    private static int stepBack(Map<String, Set<String>> map, Map<String, Integer> cache, String start) {
        if (STOP1.equals(start)) {
            return 1;
        }

        if (!cache.containsKey(start)) {
            var temp = map.entrySet().stream()
                    .filter(e -> e.getValue().contains(start))
                    .map(Map.Entry::getKey)
                    .mapToInt(k -> stepBack(map, cache, k))
                    .reduce(0, Integer::sum);

            cache.put(start, temp);
        }

        return cache.get(start);
    }

    @Override
    public Long solve2(List<Day11.Input> parsedInput) {
        var map = parsedInput.stream()
                .collect(toMap(Input::label, Input::outputs));

        return stepInto(map,
                new ConcurrentHashMap<>(),
                new HashSet<>(Set.of("dac", "fft")),
                "svr");
    }

    private static long stepInto(Map<String, Set<String>> data,
                                 Map<String, Long> cache,
                                 Set<String> required,
                                 String current) {

        String key = current + "," + join(",", required);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (STOP2.equals(current)) {
            return required.isEmpty() ? 1 : 0;
        }

        var newRequired = new HashSet<>(required);
        newRequired.remove(current);

        var temp = data.get(current).stream()
                .map(v -> stepInto(data, cache, newRequired, v))
                .reduce(0L, Long::sum);

        cache.put(key, temp);

        return temp;
    }

    public record Input(String label, Set<String> outputs) {
        public static Input parse(String s) {
            var a = s.split(": ");

            return new Input(a[0], Set.of(a[1].split(" ")));
        }
    }
}
