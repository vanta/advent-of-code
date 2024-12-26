package pl.vanta.adventofcode.year2024;

import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

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
        return 1;
    }

    @Override
    public Integer solve2(Input input) {
        return 1;
    }

    public record Input(List<int[]> locks, List<int[]> keys) {

    }
}
