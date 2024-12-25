package pl.vanta.adventofcode.year2024;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vanta.adventofcode.ParserSolverGeneric;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

public class Day24 implements ParserSolverGeneric<Day24.Input, Long, String> {
    private static final String REGEX = "([a-z0-9]{3}) (AND|OR|XOR) ([a-z0-9]{3}) -> ([a-z0-9]{3})";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public int getDayNumber() {
        return 24;
    }

    @Override
    public Input parse(String lines) {
        var scanner = new Scanner(lines).useDelimiter("\n");
        var results = scanner.tokens()
                .map(line -> line.split(": "))
                .filter(parts -> parts.length == 2)
                .collect(toMap(
                        parts -> parts[0],
                        parts -> parseInt(parts[1])
                ));

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
                .collect(toMap(
                        w -> w.output,
                        w -> w
                ));

        return new Input(wires, results);
    }

    @Override
    public Long solve(Input input) {
        return input.wires.values().stream()
                .filter(w -> w.output.startsWith("z"))
                .map(w -> {
                    long r = evaluate(w, input.wires, new TreeMap<>(input.results));
                    var shift = parseInt(w.output.substring(1));

                    return r << shift;
                })
                .reduce(0L, Long::sum);
    }

    private Integer evaluate(Wire wire, Map<String, Wire> wires, Map<String, Integer> results) {
        if (results.containsKey(wire.output)) {
            return results.get(wire.output);
        }

        if (!results.containsKey(wire.input1)) {
            results.put(wire.input1, evaluate(wires.get(wire.input1), wires, results));
        }
        if (!results.containsKey(wire.input2)) {
            results.put(wire.input2, evaluate(wires.get(wire.input2), wires, results));
        }

        var result = wire.op.calc(results.get(wire.input1), results.get(wire.input2));

        results.put(wire.output, result);

        return result;
    }

    @Override
    public String solve2(Input input) {
        input.wires.values().stream()
                .sorted(comparing(Wire::output))
                .forEach(w -> System.out.printf("%s %s %s -> %s%n", w.input1, w.op, w.input2, w.output));

        return "";
    }

    private enum Operation {
        AND, OR, XOR;

        Integer calc(Integer i1, Integer i2) {
            return switch (this) {
                case AND -> i1 & i2;
                case OR -> i1 | i2;
                case XOR -> i1 ^ i2;
            };
        }
    }

    private record Wire(String input1, Operation op, String input2, String output) {
    }

    public record Input(Map<String, Wire> wires, Map<String, Integer> results) {
    }
}
