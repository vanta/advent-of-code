package pl.vanta.adventofcode.year2024;

import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Math.floorDiv;
import static java.util.Arrays.stream;
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

    @Override
    public Long solve2(List<Integer> input) {
        return 0L;
    }
}
