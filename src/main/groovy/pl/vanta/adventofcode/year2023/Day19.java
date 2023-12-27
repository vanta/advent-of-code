package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

public class Day19 implements ParserSolver<Day19.Input, Long> {
    private static final String FIRST = "in";

    @Override
    public int getDayNumber() {
        return 19;
    }

    @Override
    public Input parse(String lines) {
        var tmp = lines.split("\n\n");

        var rules = Stream.of(tmp[0].split("\n"))
                .collect(toMap(
                        Day19::getWorkflowName,
                        Day19::getRules
                ));

        var parts = Stream.of(tmp[1].split("\n"))
                .map(Day19::getPart)
                .toList();

        return new Input(rules, parts);
    }

    private static Map<String, Integer> getPart(String s) {
        return Stream.of(s.substring(1, s.length() - 1).split(","))
                .map(p -> p.split("="))
                .collect(toMap(
                        p -> p[0],
                        p -> parseInt(p[1])
                ));
    }

    private static List<Rule> getRules(String s) {
        var substring = s.substring(s.indexOf("{") + 1, s.indexOf("}"));
        return Stream.of(substring.split(","))
                .map(Rule::parseRule)
                .toList();
    }

    private static String getWorkflowName(String s) {
        return s.substring(0, s.indexOf("{"));
    }

    @Override
    public Long solve(Input parsedInput) {
//        parsedInput.parts.forEach(System.out::println);
//        parsedInput.rules.forEach((k, v) -> System.out.println(k + " -> " + v));

        var accepted = process(parsedInput.rules, parsedInput.parts);

        return (long) accepted.stream()
                .map(p -> p.values().stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solve2(Input parsedInput) {
//        parsedInput.parts.forEach(System.out::println);
//        parsedInput.rules.forEach((k, v) -> System.out.println(k + " -> " + v));

        return traverse(parsedInput.rules, FIRST, new HashMap<>(Map.of(
                "x", new Range(1, 4000),
                "m", new Range(1, 4000),
                "a", new Range(1, 4000),
                "s", new Range(1, 4000)
        )));
    }

    private long traverse(Map<String, List<Rule>> allRules, String start, Map<String, Range> ranges) {
        return allRules.get(start).stream()
                .mapToLong(r -> countAccepted(allRules, reduceRange(ranges, r), r))
                .sum();
    }

    private Map<String, Range> reduceRange(Map<String, Range> ranges, Rule rule) {
        var newRanges = new HashMap<>(ranges);

        if (rule.hasCondition()) {
            var range = ranges.get(rule.field);

            if (rule.condition == '<') {
                newRanges.put(rule.field, range.reduceTo(rule.value() - 1));
                ranges.put(rule.field, range.reduceFrom(rule.value()));
            }

            if (rule.condition == '>') {
                newRanges.put(rule.field, range.reduceFrom(rule.value() + 1));
                ranges.put(rule.field, range.reduceTo(rule.value()));
            }
        }

        return newRanges;
    }

    private long countAccepted(Map<String, List<Rule>> allRules, Map<String, Range> ranges, Rule rule) {
        return switch (rule.result) {
            case "R" -> 0L;
            case "A" -> ranges.values().stream().map(Range::length).reduce(1L, (a, b) -> a * b);
            default -> traverse(allRules, rule.result, ranges);
        };
    }

    private List<Map<String, Integer>> process(Map<String, List<Rule>> workflows, List<Map<String, Integer>> parts) {
        return parts.stream()
                .filter(p -> getResult(p, workflows).isAccepted())
                .toList();
    }

    private Rule getResult(Map<String, Integer> part, Map<String, List<Rule>> rules) {
        var currentRules = rules.get(FIRST);
        Rule rule;

        while (!(rule = getMatchingRule(part, currentRules)).isFinal()) {
            currentRules = rules.get(rule.result);
        }

        return rule;
    }

    private static Rule getMatchingRule(Map<String, Integer> part, List<Rule> rules) {
        return rules.stream()
                .filter(r -> r.matches(part))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No rule found for: " + part));
    }

    public record Input(Map<String, List<Day19.Rule>> rules, List<Map<String, Integer>> parts) {
    }

    private record Range(int from, int to) {
        long length() {
            return from > to
                    ? 0
                    : to - from + 1;
        }

        Range reduceFrom(int val) {
            return new Range(Math.max(from, val), to);
        }

        Range reduceTo(int val) {
            return new Range(from, Math.min(to, val));
        }
    }

    private record Rule(String field, char condition, int value, String result) {
        static Rule parseRule(String p) {
            var i = p.indexOf(":");

            if (i == -1) {
                return new Rule(null, (char) 0, 0, p);
            } else {
                var igt = p.indexOf(">");
                if (igt >= 0) {
                    return new Rule(p.substring(0, igt), '>', parseInt(p.substring(igt + 1, i)), p.substring(i + 1));
                }

                var ilt = p.indexOf("<");
                if (ilt >= 0) {
                    return new Rule(p.substring(0, ilt), '<', parseInt(p.substring(ilt + 1, i)), p.substring(i + 1));
                }

                throw new RuntimeException("Unknown rule: " + p);
            }
        }

        boolean hasCondition() {
            return condition != 0;
        }

        boolean isAccepted() {
            return Objects.equals(result, "A");
        }

        boolean isFinal() {
            return result.length() == 1;
        }

        boolean matches(Map<String, Integer> part) {
            if (field == null) {
                return true;
            }

            var value = part.get(field);

            if (value == null) {
                throw new RuntimeException("Unknown field: " + field);
            }

            if (condition == '>') {
                return value > this.value;
            }

            if (condition == '<') {
                return value < this.value;
            }

            throw new RuntimeException("Unknown condition: " + condition);
        }
    }
}
