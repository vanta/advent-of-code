package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

public class Day19 implements ParserSolver<Day19.Input, Integer> {
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
        return Stream.of(s.substring(1, s.length()-1).split(","))
                .map(p -> p.split("="))
                .collect(toMap(
                        p -> p[0],
                        p -> parseInt(p[1])
                ));
    }

    private static List<Rule> getRules(String s) {
        return Stream.of(s.substring(s.indexOf("{")).split(","))
                .map(Rule::parseRule)
                .toList();
    }

    private static String getWorkflowName(String s) {
        return s.substring(0, s.indexOf("{"));
    }

    @Override
    public Integer solve(Input parsedInput) {
        parsedInput.parts.forEach(System.out::println);
        parsedInput.rules.forEach((k, v) -> System.out.println(k + " -> " + v));

        var accepted = process(parsedInput.rules, parsedInput.parts);

        return accepted.stream()
                .map(p -> p.values().stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    private List<Map<String, Integer>> process(Map<String, List<Rule>> workflows, List<Map<String, Integer>> parts) {
        return parts.stream()
                .filter(p -> isAccepted(p, workflows))
                .toList();
    }

    private boolean isAccepted(Map<String, Integer> part, Map<String, List<Rule>> rules) {
        var first = rules.get(FIRST);

        return false;
    }

    @Override
    public Integer solve2(Input parsedInput) {
        return 0;
    }

    public record Input(Map<String, List<Rule>> rules, List<Map<String, Integer>> parts) {
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

        boolean isAccepted() {
            return Objects.equals(result, "A");
        }

        boolean isRejected() {
            return Objects.equals(result, "R");
        }
    }
}
