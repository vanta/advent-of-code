package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.*;

public class Day2 implements ParserSolver<List<List<Integer>>, Integer> {
    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public List<List<Integer>> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(s -> s.split("\s+"))
                .map(a -> Stream.of(a).map(Integer::parseInt).toList())
                .toList();
    }

    @Override
    public Integer solve(List<List<Integer>> parsedInput) {
        return (int)parsedInput.stream()
                .map(l -> l.stream().toList())
                .filter(list -> isDecreasing(list) || isIncreasing(list))
                .count();
    }

    private boolean isDecreasing(List<Integer> list) {
        return range(1, list.size())
                .map(i -> list.get(i - 1) - list.get(i))
                .allMatch(i -> i > 0 && i < 4);
    }

    private boolean isIncreasing(List<Integer> list) {
        return range(1, list.size())
                .map(i -> list.get(i) - list.get(i - 1))
                .allMatch(i -> i > 0 && i < 4);
    }

    @Override
    public Integer solve2(List<List<Integer>> parsedInput) {

        return -1;
    }

}
