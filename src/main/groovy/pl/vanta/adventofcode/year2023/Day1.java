package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;
import static java.util.Collections.reverseOrder;
import static java.util.Optional.of;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.StringUtils.reverse;

public class Day1 implements ParserSolver<List<String>, Integer> {
    private static final Map<String, String> DIGITS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return parsedInput.stream()
                .map(s -> firstDigit(s) + lastDigit(s))
                .map(s -> parseInt(s, 10))
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return parsedInput.stream()
                .map(s -> firstDigit2(s) + lastDigit2(s))
                .map(s -> parseInt(s, 10))
                .reduce(0, Integer::sum);
    }

    private String firstDigit(String s) {
        return String.valueOf(s.replaceFirst("^[a-z]*", "").charAt(0));
    }

    private String lastDigit(String s) {
        return reverse(firstDigit(reverse(s)));
    }

    private String firstDigit2(final String s) {
        return findDigit(range(0, s.length()).boxed(), s);
    }

    private String lastDigit2(final String s) {
        return findDigit(range(0, s.length()).boxed().sorted(reverseOrder()), s);
    }

    private static String findDigit(Stream<Integer> range, String s) {
        return range
                .map(s::substring)
                .map(Day1::getDigit)
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Optional<String> getDigit(String substring) {
        if (isDigit(substring.charAt(0))) {
            return of(String.valueOf(substring.charAt(0)));
        } else {
            return DIGITS.keySet().stream()
                    .filter(substring::startsWith)
                    .findFirst()
                    .map(DIGITS::get);
        }
    }
}
