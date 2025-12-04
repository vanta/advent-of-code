package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

public class Day3 extends BaseDay<List<int[]>, Long> {
    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public List<int[]> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(s -> s.chars().map(c -> c - '0').toArray())
                .toList();
    }

    @Override
    public Long solve(List<int[]> parsedInput) {
        return parsedInput.stream()
                .mapToLong(s -> solve(s, 2))
                .sum();
    }

    @Override
    public Long solve2(List<int[]> parsedInput) {
        return parsedInput.stream()
                .mapToLong(s -> solve(s, 12))
                .sum();
    }

    private static long solve(int[] digits, int len) {
        var result = new StringBuilder();
        int maxIndex = -1;
        for (int i = 0; i < len; i++) {
            maxIndex = findMaxIndex(digits, maxIndex + 1, len - i - 1);
            result.append(digits[maxIndex]);
        }

        return Long.parseLong(result.toString());
    }

    private static int findMaxIndex(int[] digits, int start, int margin) {
        int max = start;
        for (int i = start; i < digits.length - margin; i++) {
            if (digits[i] > digits[max]) {
                max = i;
            }
        }
        return max;
    }
}
