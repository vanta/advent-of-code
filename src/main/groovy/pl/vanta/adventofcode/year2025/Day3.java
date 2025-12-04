package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day3 extends BaseDay<List<String>, Integer> {
    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return parsedInput.stream()
                .mapToInt(Day3::maxJoltage)
                .sum();
    }

    private static int maxJoltage(String s) {
        int max = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                var joltage = parseInt("" + s.charAt(i) + s.charAt(j));

                if (joltage > max) {
                    max = joltage;
                }
            }
        }

        return max;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return 0;
    }
}
