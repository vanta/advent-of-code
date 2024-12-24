package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Math.floorDiv;
import static java.util.Arrays.stream;
import static java.util.Collections.indexOfSubList;
import static java.util.stream.Stream.iterate;

public class Day22 implements ParserSolver<List<Integer>, Long> {

    @Override
    public int getDayNumber() {
        return 22;
    }

    @Override
    public List<Integer> parse(String lines) {
        return stream(lines.split("\n"))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public Long solve(List<Integer> input) {
        return input.stream()
                .map(i -> secret(i, 2000))
                .reduce(0L, Long::sum);
    }

    private long secret(long initialSecret, int howMany) {
        return iterate(initialSecret, this::nextSecret)
                .limit(howMany + 1)
                .reduce(0L, (l1, l2) -> l2);
    }

    @Override
    public Long solve2(List<Integer> input) {
        var windows = allWindows();

        return input.stream()
                .map(i -> secret2(i, 2000, windows))
                .reduce(0L, Long::sum);
    }

    private long secret2(long initialSecret, int howMany, List<List<Integer>> windows) {
        var digits = iterate(initialSecret, this::nextSecret)
                .limit(howMany + 1)
                .map(l -> (int) (l % 10))
                .toList();

        var diffs = iterate(1, i -> i + 1)
                .limit(digits.size() - 1)
                .map(i -> digits.get(i) - digits.get(i - 1))
                .toList();

        return windows.stream()
                .map(window -> indexOfSubList(diffs, window))
                .filter(i -> i != -1)
                .map(i -> digits.get(i + 4))
                .max(Integer::compareTo)
                .orElse(0);
    }

//    public static List<List<Long>> windows(List<Long> list, int windowSize) {
//        if (list.size() < windowSize) {
//            return List.of(); //
//        }
//
//        return range(0, list.size() - windowSize + 1)
//                .mapToObj(start -> list.subList(start, start + windowSize))
//                .toList();
//    }

    public static List<List<Integer>> allWindows() {
        List<List<Integer>> sequences = new ArrayList<>();
        for (int i = -9; i <= 9; i++) {
            for (int j = -9; j <= 9; j++) {
                for (int k = -9; k <= 9; k++) {
                    for (int l = -9; l <= 9; l++) {
                        sequences.add(List.of(i, j, k, l));
                    }
                }
            }
        }
        return sequences;
    }

    private long nextSecret(long secret) {
        var nextSecret = secret;

        nextSecret = prune(mix(nextSecret, nextSecret * 64));
        nextSecret = prune(mix(nextSecret, floorDiv(nextSecret, 32)));
        nextSecret = prune(mix(nextSecret, nextSecret * 2048));

        return nextSecret;
    }

    private long mix(long secret, long value) {
        return secret ^ value;
    }

    private long prune(long secret) {
        return secret % 16777216;
    }
}
