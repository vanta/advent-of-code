package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Day8 implements ParserSolver<Day8.Navigation, Integer> {

    private static final String START = "AAA";
    private static final String STOP = "ZZZ";

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
    public Integer solve(Navigation parsedInput) {
        var instructionsSupplier = new Supplier<Character>() {
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

        String current = START;
        int steps = 0;

        while (!current.equals(STOP)) {
            steps++;

            var currentNode = parsedInput.nodes().get(current);
            var dir = instructionsSupplier.get();

            current = dir == 'L' ? currentNode.left() : currentNode.right();
        }

        return steps;
    }

    @Override
    public Integer solve2(Navigation parsedInput) {
        return -100;
    }

    record Navigation(String instructions, Map<String, Node> nodes) {
    }

    private record Node(String name, String left, String right) {
    }
}
