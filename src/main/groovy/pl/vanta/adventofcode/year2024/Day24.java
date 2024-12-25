package pl.vanta.adventofcode.year2024;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import pl.vanta.adventofcode.ParserSolver;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.*;

public class Day24 implements ParserSolver<Day24.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 24;
    }

    @Override
    public Input parse(String lines) {
        Scanner scanner = new Scanner(lines);

        var inputs = generate(scanner::nextLine)
                .takeWhile(not(String::isEmpty))
                .map(line -> line.split(": "))
                .collect(toMap(
                        parts -> parts[0],
                        parts -> parts[1].equals("1"))
                );

//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            if(line.isEmpty()) {
//                break;
//            }
//            var parts = line.split(": ");
//            inputs.put(parts[0], parts[1].equals("1"));
//        }

        return new Input(inputs, List.of());
    }

    @Override
    public Integer solve(Input input) {
        return 1;
    }

    @Override
    public Integer solve2(Input input) {
        return 1;
    }

    private enum Operation {
        AND, OR, XOR
    }

    private record Wire(String input1, String input2, Operation operation, String output) {
    }

    public record Input(Map<String, Boolean> inputs, List<Wire> wires) {
    }
}
