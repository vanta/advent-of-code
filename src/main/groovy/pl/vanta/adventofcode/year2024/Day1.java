package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.range;

public class Day1 implements ParserSolver<List<Pair<Integer, Integer>>, Integer> {
    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<Pair<Integer, Integer>> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(s -> s.split("\s+"))
                .map(a -> Pair.of(parseInt(a[0].trim()), parseInt(a[1].trim())))
                .toList();
    }

    @Override
    public Integer solve(List<Pair<Integer, Integer>> parsedInput) {
        var list1 = parsedInput.stream()
                .map(Pair::getLeft)
                .sorted()
                .toList();

        var list2 = parsedInput.stream()
                .map(Pair::getRight)
                .sorted()
                .toList();

        return range(0, parsedInput.size())
                .map(i -> abs(list1.get(i) - list2.get(i)))
                .sum();
    }

    @Override
    public Integer solve2(List<Pair<Integer, Integer>> parsedInput) {
        var list = parsedInput.stream()
                .map(Pair::getLeft)
                .sorted()
                .toList();

        var histogram = parsedInput.stream()
                .map(Pair::getRight)
                .collect(groupingBy(i -> i, counting()));

        return (int) list.stream()
                .map(key -> key * histogram.getOrDefault(key, 0L))
                .mapToLong(i -> i)
                .sum();
    }

}
