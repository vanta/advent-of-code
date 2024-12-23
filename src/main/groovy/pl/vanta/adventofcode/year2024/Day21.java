package pl.vanta.adventofcode.year2024;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.Map.entry;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.repeat;

public class Day21 implements ParserSolver<List<String>, Integer> {

    private static final Map<Character, Location> NUMPAD = Map.ofEntries(
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
    );

    private static final Map<String, Set<String>> DIRPAD_PATHS = Map.ofEntries(
        entry("A -> ^", Set.of("<")),
        entry("A -> >", Set.of("v")),
        entry("A -> v", Set.of("<v", "v<")),
        entry("A -> <", Set.of("v<<", "<v<")),

        entry("^ -> A", Set.of(">")),
        entry("^ -> >", Set.of(">v", "v>")),
        entry("^ -> v", Set.of("v")),
        entry("^ -> <", Set.of("v<")),

        entry("> -> A", Set.of("^")),
        entry("> -> ^", Set.of("^<", "<^")),
        entry("> -> v", Set.of("<")),
        entry("> -> <", Set.of("<<")),

        entry("v -> A", Set.of("^>", ">^")),
        entry("v -> ^", Set.of("^")),
        entry("v -> >", Set.of(">")),
        entry("v -> <", Set.of("<")),

        entry("< -> A", Set.of(">^>", ">>^")),
        entry("< -> ^", Set.of(">^")),
        entry("< -> >", Set.of(">>")),
        entry("< -> v", Set.of(">"))
    );

    private static final Map<String, String> CACHE = new HashMap<>();

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
                .map(s -> complexity(s, 3))
                .reduce(0, Integer::sum);
    }

    private int complexity(String s, int maxLevel) {
        return complexityInternal(s, 0, maxLevel).length() * parseInt(s.substring(0, s.length() - 1));
    }

    private String complexityInternal(String s, int level, int maxLevel) {
//        System.out.printf("lvl=%d, cmpx for %s%n", level, s);

        if (level == maxLevel) {
            return s;
        }

        if(level > 0 && CACHE.containsKey(s)) {
            return CACHE.get(s);
        }

        String result = "";
        char current = 'A';

        for (char next : s.toCharArray()) {
            Set<String> paths = level == 0
                    ? generatePathsNumeric(current, next)
                    : generatePathsArrows(current, next);

            result += paths.stream()
                    .map(p -> complexityInternal(p, level + 1, maxLevel))
                    .min(comparingInt(String::length))
                    .orElseThrow();
//            result += " ";

            current = next;
        }

//        System.out.printf("lvl=%d, cmpx of %s is %d%n", level, s, result.length());

        if(level > 0) {
            CACHE.put(s, result);
        }

        return result;
    }

    private Set<String> generatePathsNumeric(char current, char next) {
        return findSequence(NUMPAD.get(current), NUMPAD.get(next));
    }

    private Set<String> generatePathsArrows(char current, char next) {
        if (current == next) {
            return Set.of("A");
        }

        return DIRPAD_PATHS.get(current + " -> " + next).stream()
                .map(s -> s + "A")
                .collect(toSet());
    }

    @Override
    public Integer solve2(List<String> input) {
        return input.stream()
                .map(s -> complexity(s, 25))
                .reduce(0, Integer::sum);
    }

    private static Set<String> findSequence(Location current, Location next) {
        int dx = next.x() - current.x();
        int dy = next.y() - current.y();

        if(dx < 0 && dy < 0 && current.x() == 3 && next.y() == 0) {
            return Set.of(addX(dx) + addY(dy) + "A");
        }

        if(dx > 0 && dy > 0 && current.y() == 0 && next.x() == 3) {
            return Set.of(addY(dy) + addX(dx) + "A");
        }

        return Stream.of(
                        addY(dy) + addX(dx),
                        addX(dx) + addY(dy)
                )
                .map(s -> s + "A")
                .collect(toSet());
    }

    private static String addX(int dx) {
        if (dx > 0) {
            return repeat('v', dx);
        } else if (dx < 0) {
            return repeat('^', -dx);
        }
        return "";
    }

    private static String addY(int dy) {
        if (dy > 0) {
            return repeat('>', dy);
        } else if (dy < 0) {
            return repeat('<', -dy);
        }
        return "";
    }

}
