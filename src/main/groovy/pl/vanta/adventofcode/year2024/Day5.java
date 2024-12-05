package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public class Day5 implements ParserSolver<Day5.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public Input parse(String lines) {
        var rules = stream(lines.split("\n"))
                .filter(s -> s.contains("|"))
                .map(s -> s.split("\\|"))
                .map(s -> Pair.of(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
                .collect(toSet());

        var updates = stream(lines.split("\n"))
                .filter(s -> s.contains(","))
                .map(s -> stream(s.split(",")).map(Integer::parseInt).toList())
                .toList();

        return new Input(rules, updates);
    }

    @Override
    public Integer solve(Input parsedInput) {
        return parsedInput.updates.stream()
                .filter(l -> isCorrect(l, parsedInput.rules))
                .map(l -> l.get(l.size() / 2))
                .reduce(Integer::sum)
                .orElse(0);
    }

    private boolean isCorrect(List<Integer> update, Set<Pair<Integer, Integer>> rules) {
        for (int i = 0; i < update.size() - 1; i++) {
            if (!rules.contains(Pair.of(update.get(i), update.get(i + 1)))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Integer solve2(Input parsedInput) {
        return parsedInput.updates.stream()
                .filter(l -> !isCorrect(l, parsedInput.rules))
                .map(l -> fix(l, parsedInput.rules))
                .peek(System.out::println)
                .map(l -> l.get(l.size() / 2))
                .reduce(Integer::sum)
                .orElse(0);
    }

    private List<Integer> fix(List<Integer> update, Set<Pair<Integer, Integer>> rules) {
        var result = new ArrayList<>(update);
        for (int i = 0; i < result.size() - 1; i++) {
            if (!rules.contains(Pair.of(result.get(i), result.get(i + 1)))) {
                var tmp = result.get(i);
                result.set(i, result.get(i + 1));
                result.set(i + 1, tmp);
            }
        }

        return isCorrect(result, rules) ? result : fix(result, rules);
    }

    record Input(Set<Pair<Integer, Integer>> rules, List<List<Integer>> updates) {
    }
}
