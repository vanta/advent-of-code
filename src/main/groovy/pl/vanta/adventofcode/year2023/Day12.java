package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return (long) parsedInput.stream()
                .map(this::getCount)
//                .peek(c -> System.out.println("Count:" + c))
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solve2(List<Row> parsedInput) {
        return parsedInput.stream()
                .map(r -> r.multiply(5))
                .map(this::getCount2)
                .peek(c -> System.out.println("Count:" + c))
                .reduce(0L, Long::sum);
    }

    private long getCount2(Row row) {
        var cache = new HashMap<Row, Integer>();

        return aaaa(row.row(), row.numbers(), cache);
    }

    private static long aaaa(String row, List<Integer> numbers, Map<Row, Integer> cache) {
        var r = new Row(row, numbers);
        if (cache.containsKey(r)) {
            return cache.get(r);
        }

        var result = 0L;

        for (int i = 0; i <= row.length(); i++) {
            for (int j = 0; j <= numbers.size(); j++) {

            }
        }

        return result;
    }

    private int getCount(Row r) {
//        var miniPattern = miniPattern(r.numbers());
//        var placeholdersCount = countInString(r.row(), '?');
//        var hashesCount = countInString(r.row(), '#');
//        var hashesToAdd = r.getSum() - hashesCount;

//        System.out.println("MiniPattern: " + miniPattern);
//        System.out.println("PlaceholdersCount: " + placeholdersCount);
//        System.out.println("HashesCount: " + hashesCount);
//        System.out.println("HashesToAdd: " + hashesToAdd);
        System.out.printf("Generating possible arrangements for row=%s", r);
        var count = count(r.row(), r.numbers());
        System.out.println(" - " + count);
        return count;
    }

    private int count(String row, List<Integer> numbers) {
        if (row.isEmpty()) {
            return numbers.isEmpty() ? 1 : 0;
        }

        var c = row.charAt(0);

        return switch (c) {
            case '.' -> count(row.substring(1), numbers);
            case '?' -> count("." + row.substring(1), numbers) + count("#" + row.substring(1), numbers);
            case '#' -> countWhenHash(row, numbers);
            default -> throw new IllegalStateException();
        };
    }

    private int countWhenHash(String row, List<Integer> numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        var n = numbers.get(0);

        if (row.length() < n || !row.chars().limit(n).allMatch(c -> c == '?' || c == '#')) {
            return 0;
        }

        var newNumbers = numbers.subList(1, numbers.size());

        if (row.length() == n) {
            return newNumbers.isEmpty() ? 1 : 0;
        }

        if (row.charAt(n) == '.') {
            return count(row.substring(n + 1), newNumbers);
        }

        if (row.charAt(n) == '?') {
            return count("." + row.substring(n + 1), newNumbers);
        }

        return 0;
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
