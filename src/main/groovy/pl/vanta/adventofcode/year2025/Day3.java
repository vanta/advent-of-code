package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Combinations;

import static java.lang.Integer.parseInt;
import static java.util.stream.StreamSupport.stream;

public class Day3 extends BaseDay<List<String>, Long> {
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
    public Long solve(List<String> parsedInput) {
        return parsedInput.stream()
                .mapToLong(Day3::maxJoltage)
                .sum();
    }

    private static long maxJoltage(String s) {
        return combinations(s.length(), 2).stream()
                .map(c -> chooseChars(s, c))
                .mapToLong(Long::parseLong)
                .max()
                .orElseThrow();
    }

    private static long maxJoltage2(String s) {
        return 0;
    }

    private static String chooseChars(String s, int[] indices) {
        var sb = new StringBuilder();

        for (int index : indices) {
            sb.append(s.charAt(index));
        }

        return sb.toString();
    }

    private static List<int[]> combinations(int n, int k) {
        return stream(new Combinations(n, k).spliterator(), false)
                .toList();
    }

    @Override
    public Long solve2(List<String> parsedInput) {
        return parsedInput.stream()
                .mapToLong(Day3::maxJoltage2)
                .sum();

    }
}
