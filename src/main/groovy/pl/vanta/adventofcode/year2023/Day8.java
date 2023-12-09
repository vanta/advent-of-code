package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Map;
import java.util.function.Function;
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
        return goDown(0, parsedInput.instructions(), START, parsedInput.nodes());
    }

    private int goDown(int steps, String instructions, String current, Map<String, Node> nodes) {
        if (current.equals(STOP)) {
            return steps;
        }

        var currentNode = nodes.get(current);

        var dir = instructions.substring(0, 1);
        var next = dir.equals("L") ? currentNode.left() : currentNode.right();

        return goDown(steps + 1, instructions.substring(1), next, nodes);
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
