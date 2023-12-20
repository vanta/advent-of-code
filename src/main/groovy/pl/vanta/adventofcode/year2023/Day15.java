package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                .peek(System.out::println)
                .map(Day15::hash)
                .peek(System.out::println)
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        Box[] boxes = new Box[256];

        var steps = parsedInput.stream()
                .map(this::step)
                .toList();

        for (Step step : steps) {
            var hash = hash(step.label);

            if (step.operation == '-') {
                if (boxes[hash] != null) {
                    boxes[hash].lenses.removeIf(l -> l.label.equals(step.label));
                }
            }

            if (step.operation == '=') {
                if (boxes[hash] == null) {
                    boxes[hash] = new Box(hash, new ArrayList<>());
                }

                var box = boxes[hash];

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

        return Stream.of(boxes)
                .filter(Objects::nonNull)
                .map(Box::getPower)
                .reduce(0, Integer::sum);
    }

    private Step step(String s) {
        return new Step(s.substring(0, 2), s.charAt(2), s.length() > 3 ? s.substring(3) : "");
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
