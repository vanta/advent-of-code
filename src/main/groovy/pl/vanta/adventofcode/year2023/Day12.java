package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
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
        return count(r.row(), r.numbers());
    }

    private int count(String row, List<Integer> numbers) {
        if (row.isEmpty()) {
            return numbers.isEmpty() ? 1 : 0;
        }

        var c = row.charAt(0);

        return switch (c) {
            case '.' -> count(row.substring(1), numbers);
            case '?' -> count("." + row.substring(1), numbers) + count("#" + row.substring(1), numbers);
            case '#' -> countWhenHash(row, numbers);
            default -> throw new IllegalStateException();
        };
    }

    private int countWhenHash(String row, List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        var n = numbers.get(0);

        if (row.length() < n || !row.chars().limit(n).allMatch(c -> c == '?' || c == '#')) {
            return 0;
        }

        var newNumbers = numbers.subList(1, numbers.size());

        if (row.length() == n) {
            return newNumbers.isEmpty() ? 1 : 0;
        }

        if (row.charAt(n) == '.') {
            return count(row.substring(n + 1), newNumbers);
        }

        if (row.charAt(n) == '?') {
            return count("." + row.substring(n + 1), newNumbers);
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
