package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Sets;

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

        return stepBack(map, new ConcurrentHashMap<>(), "out", "you");
    }

    private static int stepBack(Map<String, Set<String>> map, Map<String, Integer> cache, String start, String stop) {
        if (stop.equals(start)) {
            return 1;
        }

        if (!cache.containsKey(start)) {
            var temp = map.entrySet().stream()
                    .filter(e -> e.getValue().contains(start))
                    .map(Map.Entry::getKey)
                    .mapToInt(k -> stepBack(map, cache, k, stop))
                    .reduce(0, Integer::sum);

            cache.put(start, temp);
        }

        return cache.get(start);
    }

    @Override
    public Integer solve2(List<Day11.Input> parsedInput) {
        return (int) stepBack2(parsedInput, new ConcurrentHashMap<>(), "", "out", "svr").stream()
                .filter(s -> s.contains("fft"))
                .filter(s -> s.contains("dac"))
                .count();
    }

    private static Set<String> stepBack2(List<Input> data,
                                         Map<String, Set<String>> cache,
                                         String currentPath,
                                         String current,
                                         String stop) {
        if (stop.equals(current)) {
            return Set.of(current + "," + currentPath);
        }

//        if (!cache.containsKey(current)) {
            var temp = data.stream()
                    .filter(input -> input.outputs.contains(current))
                    .map(Input::label)
                    .map(k -> stepBack2(data, cache, k + "," + currentPath, k, stop))
                    .reduce(Set.of(), Sets::union);
            return temp;
        
//            cache.put(current, temp);
//        }

//        return cache.get(current);
    }

    public record Input(String label, Set<String> outputs) {
        public static Input parse(String s) {
            var a = s.split(": ");

            return new Input(a[0], Set.of(a[1].split(" ")));
        }
    }
}
