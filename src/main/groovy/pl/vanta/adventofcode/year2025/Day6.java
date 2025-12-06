package pl.vanta.adventofcode.year2025;

import java.util.Arrays;
import java.util.List;

public class Day6 extends BaseDay<Day6.Input, Long> {
    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public Day6.Input parse(String input) {
        var lines = input.lines()
                .map(String::trim)
                .toList();

        var numbers = lines.subList(0, lines.size() - 1).stream()
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        var operations = Arrays.stream(lines.getLast().split("\\s+"))
                .map(s -> s.charAt(0))
                .toList();

        return new Input(numbers, operations);
    }

    @Override
    public Long solve(Day6.Input parsedInput) {
        return 0L;
    }

    @Override
    public Long solve2(Day6.Input parsedInput) {
        return 0L;
    }

    public record Input(List<List<Integer>> numbers, List<Character> operations) {
    }
}
