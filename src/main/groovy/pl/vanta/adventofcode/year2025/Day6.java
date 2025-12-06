package pl.vanta.adventofcode.year2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

public class Day6 extends BaseDay<Set<Day6.Column>, Long> {
    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public Set<Day6.Column> parse(String input) {
        var lines = input.lines()
                .map(String::trim)
                .toList();

        var numbers = lines.subList(0, lines.size() - 1).stream()
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .map(Long::parseLong)
                        .toList())
                .toList();

        var operations = Arrays.stream(lines.getLast().split("\\s+"))
                .map(s -> s.charAt(0))
                .toList();

        var result = new HashSet<Day6.Column>();

        for (int i = 0; i < operations.size(); i++) {
            var nums = getColumn(numbers, i);

            var column = switch (operations.get(i)) {
                case '+' -> new Column(nums, 0L, Long::sum);
                case '*' -> new Column(nums, 1L, (a, b) -> a * b);
                default -> throw new IllegalArgumentException("Unknown operation");
            };

            result.add(column);
        }

        return result;
    }

    private static List<Long> getColumn(List<List<Long>> numbers, int i) {
        return numbers.stream()
                .map(row -> row.get(i))
                .toList();
    }

    @Override
    public Long solve(Set<Day6.Column> parsedInput) {
        return parsedInput.stream()
                .map(c -> c.numbers.stream().reduce(c.identity, c.function))
                .reduce(0L, Long::sum);
    }

    @Override
    public Long solve2(Set<Day6.Column> parsedInput) {
        return parsedInput.stream()
                .map(c -> c.numbers.stream().reduce(c.identity, c.function))
                .reduce(0L, Long::sum);
    }

    public record Column(List<Long> numbers,
                         Long identity,
                         BinaryOperator<Long> function) {
    }
}
