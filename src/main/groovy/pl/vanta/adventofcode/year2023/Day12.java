package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
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

    @Override
    public Integer solve2(List<Row> parsedInput) {
        var newRows = parsedInput.stream()
                .map(r -> r.multiply(5))
                .toList();


        return 0;
    }

    private int getCount(Row r) {
        var generate = generate(r.row(), r.getSum() - countInString(r.row(), '#'));
        var miniPattern = miniPattern(r.numbers());
//        var pattern = pattern(r.numbers());

        System.out.println("%s - %d".formatted(r, generate.size()));

        return (int) generate.stream()
                .filter(s -> matches(s, miniPattern))
//                .map(pattern::matcher)
//                .filter(Matcher::matches)
                .count();
    }

    private List<String> generate(String row, int hashesToAdd) {
        var currentPlaceholdersCount = countInString(row, '?');
        if (currentPlaceholdersCount == 0) {
            return List.of(row);
        }

        if (hashesToAdd == 0) {
            return List.of(row.replaceAll("\\?", "."));
        }

        if (currentPlaceholdersCount < hashesToAdd) {
            return List.of();
        }

        var result = new ArrayList<String>();
        result.addAll(generate(row.replaceFirst("\\?", "."), hashesToAdd));
        result.addAll(generate(row.replaceFirst("\\?", "#"), hashesToAdd - 1));
        return result;
    }

    private static int countInString(String row, char charToCount) {
        return (int) row.chars().filter(c -> c == charToCount).count();
    }

    private boolean matches(String row, String miniPattern) {
        var tmp = row.replaceAll("\\.+", ".")
                .replaceAll("^\\.?", "")
                .replaceAll("\\.?$", "");

        return miniPattern.equals(tmp);
    }

    private static String miniPattern(List<Integer> numbers) {
        return numbers.stream()
                .map("#"::repeat)
                .collect(joining("."));
    }

    private static Pattern pattern(List<Integer> numbers) {
        return Pattern.compile(".*" + numbers.stream()
                .map("#"::repeat)
                .collect(joining(".+")) + ".*");
    }

    public record Row(String row, List<Integer> numbers) {
        int getSum() {
            return numbers.stream()
                    .reduce(0, Integer::sum);
        }

        Row multiply(int i) {
            var newRow = Stream.generate(() -> row)
                    .limit(i)
                    .collect(joining("?"));

            var newNumbers = Stream.generate(() -> numbers)
                    .limit(i)
                    .flatMap(List::stream)
                    .toList();

            return new Row(newRow, newNumbers);
        }

        boolean startsWith(Row r) {
            return row.startsWith(r.row()) && numbers.subList(0, r.numbers().size()).equals(r.numbers());
        }

        boolean endsWith(Row r) {
            return row.endsWith(r.row()) && numbers.subList(numbers.size() - r.numbers().size(), numbers.size()).equals(r.numbers());
        }
    }

}
