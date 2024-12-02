package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.stream.IntStream.range;

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
        return (int) parsedInput.stream()
                .map(l -> l.stream().toList())
                .filter(this::isSafe)
                .count();
    }

    private boolean isSafe(List<Integer> list) {
        return isDecreasing(list) || isIncreasing(list);
    }

    private boolean isSafe2(List<Integer> list) {
        if (isSafe(list)) {
            return true;
        } else {
            return range(0, list.size())
                    .mapToObj(i -> {
                        var copy = new ArrayList<>(list);
                        copy.remove(i);
                        return copy;
                    })
                    .anyMatch(this::isSafe);
        }
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
        return (int) parsedInput.stream()
                .map(l -> l.stream().toList())
                .filter(this::isSafe2)
                .count();

    }

}
