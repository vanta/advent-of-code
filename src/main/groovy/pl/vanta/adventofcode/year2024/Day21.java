package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.apache.commons.lang3.StringUtils.repeat;

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
                .map(this::complexity)
                .reduce(0, Integer::sum);
    }

    private int complexity(String s) {
        var seq = seq(s);
        var substring = s.substring(0, s.length() - 1);

        System.out.printf("seq.l=%d, sub=%s%n", seq.length(), substring);

        return seq.length() * parseInt(substring);
    }

    private String seq(String s) {
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

        var sequence = numericKeypad.sequence(s);
        var sequence1 = directionalKeypad1.sequence(sequence);
        var sequence2 = directionalKeypad2.sequence(sequence1);

        System.out.println(sequence2);
        System.out.println(sequence1);
        System.out.println(sequence);
        System.out.println(s);

        return sequence2;
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
            var result = "";
            int dx = next.x() - current.x();
            int dy = next.y() - current.y();

            if(dx < 0 && dy < 0) {
                result = addX(dx, result);
                result = addY(dy, result);
            } else if (dx > 0 && dy > 0){
                result = addY(dy, result);
                result = addX(dx, result);
            } else if (dx > 0 && dy < 0){
                result = addX(dx, result);
                result = addY(dy, result);
            } else if (dx < 0 && dy > 0){
                result = addY(dy, result);
                result = addX(dx, result);
            }  else {
                result = addY(dy, result);
                result = addX(dx, result);
            }

            return result;
        }

        private static String addX(int dx, String result) {
            if (dx > 0) {
                result += repeat('v', dx);
            } else if (dx < 0) {
                result += repeat('^', -dx);
            }
            return result;
        }

        private static String addY(int dy, String result) {
            if (dy > 0) {
                result += repeat('>', dy);
            } else if (dy < 0) {
                result += repeat('<', -dy);
            }
            return result;
        }
    }

}
