package pl.vanta.adventofcode.year2024;

import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static com.google.common.collect.Lists.cartesianProduct;
import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;

public class Day25 implements ParserSolver<Day25.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 25;
    }

    @Override
    public Input parse(String lines) {
        var locks = stream(lines.split("\n\n"))
                .map(s -> s.split("\n"))
                .filter(this::isLock)
                .map(Day25::mapLockOrKey)
                .toList();

        var keys = stream(lines.split("\n\n"))
                .map(s -> s.split("\n"))
                .filter(not(this::isLock))
                .map(Day25::mapLockOrKey)
                .toList();

        return new Input(keys, locks);
    }

    private boolean isLock(String[] s) {
        return "#####".equals(s[0]);
    }

    private static int[] mapLockOrKey(String[] s) {
        int[] result = new int[5];

        stream(s)
                .skip(1)
                .limit(s.length - 2)
                .forEach(s1 -> {
                    var chars = s1.toCharArray();

                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] == '#') {
                            result[i]++;
                        }
                    }
                });

        return result;
    }

    @Override
    public Integer solve(Input input) {
        var result = cartesianProduct(input.locks, input.keys)
                .stream()
                .filter(p -> fits(p.get(0), p.get(1)))
                .toList();

        return result.size();
    }

    private boolean fits(int[] lock, int[] key) {
        for (int i = 0; i < 5; i++) {
            if (lock[i] + key[i] > 5) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Integer solve2(Input input) {
        return 1;
    }

    public record Input(List<int[]> locks, List<int[]> keys) {

    }
}
