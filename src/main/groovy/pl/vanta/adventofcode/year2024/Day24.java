package pl.vanta.adventofcode.year2024;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

public class Day24 implements ParserSolver<Day24.Input, Long> {
    private static final String REGEX = "([a-z0-9]{3}) (AND|OR|XOR) ([a-z0-9]{3}) -> ([a-z0-9]{3})";
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
                .map(parts -> new Wire(null, null, parts[0], (u, v) -> parseInt(parts[1])))
                .toList();

        scanner = new Scanner(lines).useDelimiter("\n");
        var wires = scanner.tokens()
                .map(PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcher -> new Wire(
                        matcher.group(1),
                        matcher.group(3),
                        matcher.group(4),
                        (u, v) -> Operation.valueOf(matcher.group(2)).calc(u, v)
                ))
                .toList();

        var result = Stream.of(inputs, wires)
                .flatMap(Collection::stream)
                .collect(toMap(w -> w.output, w -> w));

        return new Input(result);
    }

    @Override
    public Long solve(Input input) {
        long result = input.wires.values().stream()
                .filter(w -> w.output.startsWith("z"))
                .map(w -> {
                    long r = w.getValue(input.wires);
                    var shift = parseInt(w.output.substring(1));

                    return r << shift;
                })
                .reduce(0L, Long::sum);

        return result;
    }

//    private Integer evaluate(WireDef wire, Map<String, WireDef> wires, Map<String, Integer> results) {
//        if (!results.containsKey(wire.input1)) {
//            var r = evaluate(wires.get(wire.input1), wires, results);
//            results.put(wire.input1, r);
//        }
//
//        if (!results.containsKey(wire.input2)) {
//            var r = evaluate(wires.get(wire.input2), wires, results);
//            results.put(wire.input2, r);
//        }
//
//        var i1 = results.get(wire.input1);
//        var i2 = results.get(wire.input2);
//
//        return wire.calc(i1, i2);
//    }

    @Override
    public Long solve2(Input input) {
        return 1L;
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

    record Wire(String input1, String input2, String output, BiFunction<Integer, Integer, Integer> function) {
        Integer getValue(Map<String, Wire> wires) {
            var i1 = getInput(wires, input1);
            var i2 = getInput(wires, input2);

            return function.apply(i1, i2);
        }

        private Integer getInput(Map<String, Wire> wires, String input) {
            if(input == null) {
                return null;
            }

            return wires.get(input).getValue(wires);
        }
    }

    public record Input(Map<String, Wire> wires) {
    }
}
