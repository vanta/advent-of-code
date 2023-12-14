package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Day12 implements ParserSolver<List<Day12.Row>, Integer> {

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public List<Row> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day12::parseRow)
                .toList();
    }

    private static Row parseRow(String s) {
        var split = s.split(" ");
        var numbers = Stream.of(split[1].split(","))
                .map(Integer::parseInt)
                .toList();

        return new Row(split[0], numbers);
    }

    @Override
    public Integer solve(List<Row> parsedInput) {
        parsedInput.forEach(System.out::println);
        parsedInput.stream()
                .map(r -> matches(r.row(), r.numbers()))
                .forEach(System.out::println);

        return 0;
    }

    private boolean matches(String row, List<Integer> numbers) {
        var tmp = row.replaceAll("\\.+", ".")
                .replaceAll("^\\.?", "")
                .replaceAll("\\.?$", "");

        var c = numbers.stream()
                .map("#"::repeat)
                .collect(joining("."));

        return c.equals(tmp);
    }

    @Override
    public Integer solve2(List<Row> parsedInput) {
        return 0;

    }

    public record Row(String row, List<Integer> numbers) {
    }

}
