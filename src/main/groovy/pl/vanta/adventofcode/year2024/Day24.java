package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.vanta.adventofcode.ParserSolverGeneric;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.joining;
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
                .map(m -> new Wire(m.group(1), Operation.valueOf(m.group(2)), m.group(3), m.group(4)))
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
                .map(w -> result(input, w))
                .reduce(0L, Long::sum);
    }

    private long result(Input input, Wire w) {
        long r = evaluate(w, input.wires, new TreeMap<>(input.results));
        var shift = parseInt(w.output.substring(1));

        return r << shift;
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
        var map = new TreeMap<String, Integer>();

        int count = (int) input.wires.keySet().stream()
                .filter(k -> k.startsWith("z"))
                .count();

        for (int i = 0; i < count; i++) {
            map.put("x%02d".formatted(i), 0);
            map.put("y%02d".formatted(i), 0);
        }

        var expected = 1;
        var wrongGates = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            var newMap = new TreeMap<>(map);

            newMap.put("x%02d".formatted(i), 1);
            newMap.put("y%02d".formatted(i), 1);

            int result = evaluate(input.wires.get("z%02d".formatted(i)), input.wires, newMap);

            if (result != expected) {
                wrongGates.add("z%02d".formatted(i));
            }
        }

        return wrongGates.stream().sorted().collect(joining(","));
    }

    private enum Operation {
        AND((i1, i2) -> i1 & i2),
        OR((i1, i2) -> i1 | i2),
        XOR((i1, i2) -> i1 ^ i2);

        private final BiFunction<Integer, Integer, Integer> operation;

        Operation(BiFunction<Integer, Integer, Integer> operation) {
            this.operation = operation;
        }

        Integer calc(Integer i1, Integer i2) {
            return operation.apply(i1, i2);
        }
    }

    private record Wire(String input1, Operation op, String input2, String output) {
    }

    public record Input(Map<String, Wire> wires, Map<String, Integer> results) {
    }
}
