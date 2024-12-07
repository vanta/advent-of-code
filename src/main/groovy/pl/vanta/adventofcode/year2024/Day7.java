package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class Day7 implements ParserSolver<Map<Integer, List<Integer>>, Integer> {

    @Override
    public int getDayNumber() {
        return 7;
    }

    @Override
    public Map<Integer, List<Integer>> parse(String lines) {
        return stream(lines.split("\n"))
                .map(l -> l.split(":"))
                .collect(Collectors.toMap(
                        a -> parseInt(a[0].trim()),
                        a -> stream(a[1].trim().split(" "))
                                .map(Integer::parseInt)
                                .toList()
                ));
    }

    @Override
    public Integer solve(Map<Integer, List<Integer>> parsedInput) {
        return parsedInput.entrySet().stream()
                .filter(e -> isValid(e.getKey(), e.getValue()))
                .map(Map.Entry::getKey)
                .reduce(0, Integer::sum);
    }

    private boolean isValid(int result, List<Integer> values) {



        return false;
    }

    @Override
    public Integer solve2(Map<Integer, List<Integer>> parsedInput) {

        return 0;
    }

}
