package pl.vanta.adventofcode.year2023;

import org.apache.commons.lang3.StringUtils;
import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;
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
                .map(s -> firstDigit(s) + reverse(firstDigit(reverse(s))))
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

    private String firstDigit2(final String s) {
        for (int i = 0; i < s.length(); i++) {
            if (isDigit(s.charAt(i))) {
                return String.valueOf(s.charAt(i));
            } else {
                var o = startsWithDigit(s.substring(i));
                if (o.isPresent()) {
                    return o.get();
                }
            }
        }
        throw new IllegalStateException();
    }

    private String lastDigit2(final String p) {
        var s = reverse(p);

        for (int i = 0; i < s.length(); i++) {
            if (isDigit(s.charAt(i))) {
                return String.valueOf(s.charAt(i));
            } else {
                var o = startsWithDigitReversed(s.substring(i));
                if (o.isPresent()) {
                    return o.get();
                }
            }
        }
        throw new IllegalStateException();
    }

    private static Optional<String> startsWithDigit(String substring) {
        return DIGITS.keySet().stream()
                .filter(substring::startsWith)
                .findFirst()
                .map(DIGITS::get);
    }

    private static Optional<String> startsWithDigitReversed(String substring) {
        return DIGITS.keySet().stream()
                .map(StringUtils::reverse)
                .filter(substring::startsWith)
                .map(StringUtils::reverse)
                .findFirst()
                .map(DIGITS::get);
    }
}
