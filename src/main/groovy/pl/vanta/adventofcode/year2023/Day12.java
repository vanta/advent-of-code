package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
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
//        parsedInput.forEach(System.out::println);

        return parsedInput.stream()
                .map(this::getCount)
                .reduce(0, Integer::sum);
    }

    private int getCount(Row r) {
        var generate = generate(r.row(), r.getSum());
        System.out.println("%s - %d".formatted(r, generate.size()));
        return (int) generate.stream()
                .filter(s -> matches(s, r.numbers()))
                .count();
    }

    private List<String> generate(String row, int hashesCount) {
        if (row.indexOf('?') == -1) {
            return List.of(row);
        }

        if (row.chars().filter(c -> c == '#').count() == hashesCount) {
            return List.of(row.replaceAll("\\?", "."));
        }

        var result = new ArrayList<String>();
        result.addAll(generate(row.replaceFirst("\\?", "."), hashesCount));
        result.addAll(generate(row.replaceFirst("\\?", "#"), hashesCount));
        return result;
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
        int getSum() {
            return numbers.stream()
                    .reduce(0, Integer::sum);
        }
    }

}
