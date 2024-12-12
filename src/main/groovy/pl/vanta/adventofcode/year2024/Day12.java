package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Long.parseLong;

public class Day12 implements ParserSolver<Long[], Long> {
    private static final Map<Pair<Long, Integer>, Long> CACHE = new HashMap<>();

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public Long[] parse(String lines) {
        return Arrays.stream(lines.replace("\n", "").trim().split(" "))
                .map(Long::parseLong)
                .toArray(Long[]::new);
    }

    @Override
    public Long solve(Long[] parsedInput) {
        return blink(parsedInput, 25);
    }

    @Override
    public Long solve2(Long[] parsedInput) {
        return blink(parsedInput, 75);
    }

    private long blink(Long[] stones, int blinks) {
        return Arrays.stream(stones)
                .map(stone -> count(stone, blinks))
                .reduce(0L, Long::sum);
    }

    private long count(long n, int b) {
        if (b == 0) {
            return 1;
        }

        var key = Pair.of(n, b);

        if (!CACHE.containsKey(key)) {
            CACHE.put(key, getValue(n, b));
        }

        return CACHE.get(key);
    }

    private long getValue(long n, int b) {
        if (n == 0) {
            return count(1, b - 1);
        } else {
            var string = String.valueOf(n);

            if (string.length() % 2 == 0) {
                var size2 = string.length() / 2;
                var s1 = parseLong(string.substring(0, size2));
                var s2 = parseLong(string.substring(size2));
                return count(s1, b - 1) + count(s2, b - 1);
            } else {
                return count(n * 2024, b - 1);
            }
        }
    }

}
