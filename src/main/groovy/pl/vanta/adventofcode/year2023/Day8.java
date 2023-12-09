package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Day8 implements ParserSolver<Day8.Navigation, Long> {
    private static final String START = "AAA";
    private static final String STOP = "ZZZ";
    private static final String START_GHOST = "A";
    private static final String STOP_GHOST = "Z";

    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public Navigation parse(String lines) {
        var split = lines.split("\n");
        var nodes = Stream.of(split)
                .skip(2)
                .map(Day8::parseNode)
                .collect(toMap(
                        Node::name,
                        Function.identity()
                ));

        return new Navigation(split[0], nodes);
    }

    private static Node parseNode(String line) {
        var name = line.substring(0, 3);
        var left = line.substring(7, 10);
        var right = line.substring(12, 15);

        return new Node(name, left, right);
    }

    @Override
    public Long solve(Navigation parsedInput) {
        var instructionsSupplier = getSupplier(parsedInput);

        return (long)getSteps(START, instructionsSupplier, parsedInput.nodes(), s -> s.equals(STOP));
    }

    @Override
    public Long solve2(Navigation parsedInput) {
        var instructionsSupplier = getSupplier(parsedInput);

        var startNodes = parsedInput.nodes().values().stream()
                .map(Node::name)
                .filter(n -> n.endsWith(START_GHOST))
                .toList();

        var stopNodes = parsedInput.nodes().values().stream()
                .map(Node::name)
                .filter(n -> n.endsWith(STOP_GHOST))
                .collect(toSet());

        return startNodes.stream()
                .map(n -> getSteps(n, instructionsSupplier, parsedInput.nodes(), stopNodes::contains))
                .map(Long::valueOf)
                .reduce(1L, (x, y) -> x * (y / gcd(x, y)));
    }

    private static long gcd(long x, long y) {
        return (y == 0) ? x : gcd(y, x % y);
    }

    private static long lcm(Collection<Long> numbers) {
        return numbers.stream().reduce(1L, (x, y) -> x * (y / gcd(x, y)));
    }

    private static int getSteps(String current,
                                 Supplier<Character> instructionsSupplier,
                                 Map<String, Node> nodes,
                                 Function<String, Boolean> stopCondition) {
        var steps = 0;

        while (!stopCondition.apply(current)) {
            steps++;

            current = getNewCurrentNode(instructionsSupplier.get(), nodes.get(current));
        }

        return steps;
    }

    private static String getNewCurrentNode(Character dir, Node currentNode) {
        return dir == 'L' ? currentNode.left() : currentNode.right();
    }

    private static Supplier<Character> getSupplier(Navigation parsedInput) {
        return new Supplier<>() {
            private final String original = parsedInput.instructions();
            private int index = 0;

            @Override
            public Character get() {
                if (index == original.length()) {
                    index = 0;
                }

                return original.charAt(index++);
            }
        };
    }

    record Navigation(String instructions, Map<String, Node> nodes) {
    }

    private record Node(String name, String left, String right) {
    }
}
