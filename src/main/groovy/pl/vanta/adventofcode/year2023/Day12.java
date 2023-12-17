package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Day12 implements ParserSolver<List<Day12.Row>, Long> {

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public List<Row> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day12::parseRow)
                .toList();
    }

    private static Row parseRow(String s) {
        var split = s.split(" ");
        var numbers = Stream.of(split[1].split(","))
                .map(Integer::parseInt)
                .toList();

        return new Row(split[0], numbers);
    }

    @Override
    public Long solve(List<Row> parsedInput) {
        return (long) parsedInput.stream()
                .map(this::getCount)
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solve2(List<Row> parsedInput) {
        return parsedInput.stream()
                .map(r -> r.multiply(5))
                .map(this::getCount)
                .peek(c -> System.out.println("Count:" + c))
                .map(i -> (long) i)
                .reduce(0L, Long::sum);
    }

    private int getCount(Row r) {
        var cache = new HashMap<Row, Integer>();

        return count(r.row(), r.numbers(), cache);
    }

    private int count(String row, List<Integer> numbers, Map<Row, Integer> cache) {
        Row r = new Row(row, numbers);
        if (cache.containsKey(r)) {
            return cache.get(r);
        }

        if (row.isEmpty()) {
            return numbers.isEmpty() ? 1 : 0;
        }

        var value = switch (row.charAt(0)) {
            case '.' -> count(row.substring(1), numbers, cache);
            case '?' -> count("." + row.substring(1), numbers, cache) + count("#" + row.substring(1), numbers, cache);
            case '#' -> countWhenHash(row, numbers, cache);
            default -> throw new IllegalStateException();
        };

        cache.put(r, value);

        return value;
    }

    private int countWhenHash(String row, List<Integer> numbers, Map<Row, Integer> cache) {
        if (numbers.isEmpty()) {
            return 0;
        }

        var groupLen = numbers.get(0);

        if (row.length() >= groupLen && row.chars().limit(groupLen).allMatch(c -> c == '?' || c == '#')) {
            var newNumbers = numbers.subList(1, numbers.size());

            if (row.length() == groupLen) {
                return newNumbers.isEmpty() ? 1 : 0;
            }

            if (row.charAt(groupLen) == '.') {
                return count(row.substring(groupLen + 1), newNumbers, cache);
            }

            if (row.charAt(groupLen) == '?') {
                return count("." + row.substring(groupLen + 1), newNumbers, cache);
            }
        }

        return 0;
    }

    public record Row(String row, List<Integer> numbers) {
        Row multiply(int i) {
            var newRow = Stream.generate(() -> row)
                    .limit(i)
                    .collect(joining("?"));

            var newNumbers = Stream.generate(() -> numbers)
                    .limit(i)
                    .flatMap(List::stream)
                    .toList();

            return new Row(newRow, newNumbers);
        }
    }

}
