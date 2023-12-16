package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Day12 implements ParserSolver<List<Day12.Row>, Long> {

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
    public Long solve(List<Row> parsedInput) {
//        parsedInput.forEach(System.out::println);

        return (long) parsedInput.stream()
                .map(this::getCount)
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solve2(List<Row> parsedInput) {
        return parsedInput.stream()
                .map(r -> r.multiply(5))
                .map(this::getCount)
                .map(i -> (long) i)
                .reduce(0L, Long::sum);
    }

    private long getCount2(String row, List<Integer> numbers) {
        long[][] sol = new long[row.length() + 1][numbers.size() + 1];

        for (int i = 0; i <= row.length(); i++) {
            for (int j = 0; j <= numbers.size(); j++) {
                sol[i][j] = switch (row.charAt(i)) {
                    case '#' -> 0;
                    case '?' -> j == 0 ? 1 : sol[i - 1][j - 1] + sol[i - 1][j];
                    default -> throw new IllegalStateException();
                };
            }
        }

        return sol[row.length()][numbers.size()];
    }

    private int getCount(Row r) {
        var pattern = pattern(r.numbers());
        var generate = generate(r.row(), pattern, countInString(r.row(), '?'), r.getSum() - countInString(r.row(), '#'));
        var miniPattern = miniPattern(r.numbers());

        System.out.println("%s - %d".formatted(r, generate.size()));

        return (int) generate.stream()
                .filter(s -> matches(s, miniPattern))
//                .map(pattern::matcher)
//                .filter(Matcher::matches)
                .count();
    }

    private List<String> generate(String row, Pattern pattern, int currentPlaceholdersCount, int hashesToAdd) {
        if (currentPlaceholdersCount == 0) {
            return List.of(row);
        }

        if (currentPlaceholdersCount < hashesToAdd) {
            return List.of();
        }

        if (hashesToAdd == 0) {
            return List.of(row.replaceAll("\\?", "."));
        }

        if(!pattern.matcher(row).matches()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        result.addAll(generate(row.replaceFirst("\\?", "."), pattern, currentPlaceholdersCount - 1, hashesToAdd));
        result.addAll(generate(row.replaceFirst("\\?", "#"), pattern, currentPlaceholdersCount - 1, hashesToAdd - 1));
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
                .map("(#|\\?){%d}"::formatted)
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
