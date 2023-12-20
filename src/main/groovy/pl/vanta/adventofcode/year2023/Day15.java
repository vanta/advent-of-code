package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day15 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 15;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.trim().split(","))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return parsedInput.stream()
                .map(Day15::hash)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        var boxes = new HashMap<Integer, Box>();

        var steps = parsedInput.stream()
                .map(this::step)
                .toList();

        for (Step step : steps) {
            var hash = hash(step.label);

            if (step.operation == '-') {
                if (boxes.containsKey(hash)) {
                    boxes.get(hash).lenses.removeIf(l -> l.label.equals(step.label));
                }
            }

            if (step.operation == '=') {
                boxes.putIfAbsent(hash, new Box(hash, new ArrayList<>()));

                var box = boxes.get(hash);
                var count = (int) box.lenses.stream()
                        .takeWhile(l -> !l.label.equals(step.label))
                        .count();

                var lens = new Lens(step.label, Integer.parseInt(step.value));
                if (count < box.lenses.size()) {
                    box.lenses.set(count, lens);
                } else {
                    box.lenses.add(lens);
                }
            }
        }

        return boxes.values().stream()
                .map(Box::getPower)
                .reduce(0, Integer::sum);
    }

    private Step step(String s) {
        var eq = s.indexOf('=');
        var da = s.indexOf('-');

        if (eq >= 0) {
            return new Step(s.substring(0, eq), s.charAt(eq), s.substring(eq + 1));
        }

        if (da >= 0) {
            return new Step(s.substring(0, da), s.charAt(da), "");
        }
        return null;
    }

    private static int hash(String s) {
        return s.chars()
                .reduce(0, (left, right) -> ((left + right) * 17) % 256);
    }

    private record Box(int index, List<Lens> lenses) {
        int getPower() {
            int result = 0;
            for (int i = 0; i < lenses.size(); i++) {
                result += (i + 1) * lenses.get(i).focalLength;
            }
            return (index + 1) * result;
        }
    }

    private record Lens(String label, int focalLength) {
    }

    private record Step(String label, char operation, String value) {
    }
}
