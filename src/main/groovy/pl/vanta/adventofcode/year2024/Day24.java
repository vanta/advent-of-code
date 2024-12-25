package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.generate;

public class Day24 implements ParserSolver<Day24.Input, Integer> {
    private static final String REGEX = "([a-z]\\d\\d) (AND|OR|XOR) ([a-z]\\d\\d) -> ([a-z]\\d\\d)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public int getDayNumber() {
        return 24;
    }

    @Override
    public Input parse(String lines) {
        var scanner = new Scanner(lines).useDelimiter("\n");
        var inputs = scanner.tokens()
                .map(line -> line.split(": "))
                .filter(parts -> parts.length == 2)
                .collect(toMap(
                        parts -> parts[0],
                        parts -> parts[1].equals("1"))
                );

        scanner = new Scanner(lines).useDelimiter("\n");
        var wires = scanner.tokens()
                .map(PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Wire(
                        matcher.group(1),
                        Operation.valueOf(matcher.group(2)),
                        matcher.group(3),
                        matcher.group(4)
                ))
                .toList();

        return new Input(inputs, wires);
    }

    @Override
    public Integer solve(Input input) {
        return 1;
    }

    @Override
    public Integer solve2(Input input) {
        return 1;
    }

    private enum Operation {
        AND, OR, XOR
    }

    private record Wire(String input1, Operation operation, String input2, String output) {
    }

    public record Input(Map<String, Boolean> inputs, List<Wire> wires) {
    }
}
