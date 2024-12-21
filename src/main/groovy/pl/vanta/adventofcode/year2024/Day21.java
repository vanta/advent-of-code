package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class Day21 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 21;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        return input.stream()
                .map(s -> complexity(s) * parseInt(s.substring(0, s.length() - 1)))
                .reduce(0, Integer::sum);
    }

    private int complexity(String s) {
        var numericKeypad = new Keypad(ofEntries(
                entry('7', new Location(0, 0)),
                entry('8', new Location(0, 1)),
                entry('9', new Location(0, 2)),
                entry('4', new Location(1, 0)),
                entry('5', new Location(1, 1)),
                entry('6', new Location(1, 2)),
                entry('1', new Location(2, 0)),
                entry('2', new Location(2, 1)),
                entry('3', new Location(2, 2)),
                entry('0', new Location(3, 1)),
                entry('A', new Location(3, 2))
        ));

        var directionalKeypad1 = new Keypad(Map.of(
                '^', new Location(0, 1),
                'A', new Location(0, 2),
                '<', new Location(1, 0),
                'v', new Location(1, 1),
                '>', new Location(1, 2)
        ));

        var directionalKeypad2 = new Keypad(Map.of(
                '^', new Location(0, 1),
                'A', new Location(0, 2),
                '<', new Location(1, 0),
                'v', new Location(1, 1),
                '>', new Location(1, 2)
        ));

        return directionalKeypad2.sequence(directionalKeypad1.sequence(numericKeypad.sequence(s))).length();
    }

    @Override
    public Integer solve2(List<String> input) {

        return 0;
    }

    private record Keypad(Map<Character, Location> keys) {
        String sequence(String given) {
            var result = new StringBuilder();
            var current = keys.get('A');

            for (char toClick : given.toCharArray()) {
                var next = keys.get(toClick);

                result.append(findSequence(current, next));
                result.append('A');

                current = next;
            }

            return result.toString();
        }

        private String findSequence(Location current, Location next) {


            return null;
        }
    }

}
