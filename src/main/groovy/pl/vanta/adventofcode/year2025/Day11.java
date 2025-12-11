package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

        return lookBack(map, "out");
    }

    private static int lookBack(Map<String, Set<String>> map, String lookFor) {
        if ("you".equals(lookFor)) {
            return 1;
        }

        return map.entrySet().stream()
                .filter(e -> e.getValue().contains(lookFor))
                .map(Map.Entry::getKey)
                .mapToInt(k -> lookBack(map, k))
                .reduce(0, Integer::sum);
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

}
