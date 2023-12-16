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
        return (long) parsedInput.stream()
                .map(this::getCount)
                .peek(c -> System.out.println("Count:" + c))
                .reduce(0, Integer::sum);
    }

    @Override
    public Long solve2(List<Row> parsedInput) {
        return parsedInput.stream()
                .map(r -> r.multiply(5))
                .map(this::getCount)
                .peek(c -> System.out.println("Count:" + c))
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
        var miniPattern = miniPattern(r.numbers());
        var placeholdersCount = countInString(r.row(), '?');
        var hashesCount = countInString(r.row(), '#');
        var hashesToAdd = r.getSum() - hashesCount;

//        System.out.println("MiniPattern: " + miniPattern);
//        System.out.println("PlaceholdersCount: " + placeholdersCount);
//        System.out.println("HashesCount: " + hashesCount);
//        System.out.println("HashesToAdd: " + hashesToAdd);
        System.out.printf("Generating possible arrangements for row=%s%n", r);

        return generate(r.row(), miniPattern, pattern, placeholdersCount, hashesToAdd);
    }

    private int generate(String row, String miniPattern, Pattern pattern, int currentPlaceholdersCount, int hashesToAdd) {
        List<String> rows;
        List<String> tmp = new ArrayList<>(List.of(row));

        for (int i = 0; i <currentPlaceholdersCount; i++) {
            rows = new ArrayList<>(tmp);
            tmp.clear();
            for (String s : rows) {
                if(pattern.matcher(s).matches()) {
                    tmp.add(s.replaceFirst("\\?", "."));
                    tmp.add(s.replaceFirst("\\?", "#"));
                }
            }
        }

        return (int) tmp.stream()
                .filter(r -> matches(r, miniPattern))
                .count();
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
