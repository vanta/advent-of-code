package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashMap;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Long.parseLong;

public class Day11 implements ParserSolver<Long[], Integer> {
    @Override
    public int getDayNumber() {
        return 11;
    }

    @Override
    public Long[] parse(String lines) {
        return Arrays.stream(lines.replace("\n", "").trim().split(" "))
                .map(Long::parseLong)
                .toArray(Long[]::new);
    }

    @Override
    public Integer solve(Long[] parsedInput) {
        return blink(parsedInput, 25);
    }

    private int blink(Long[] parsedInput, int blinks) {
        var stones = parsedInput;

        var map = new HashMap<Long, Long[]>();

        for (int i = 0; i < blinks; i++) {
            System.out.print("Blink " + i + ", ");
            stones = Arrays.stream(stones)
                    .map(s -> map.computeIfAbsent(s, this::change))
                    .flatMap(Arrays::stream)
                    .toArray(Long[]::new);

            System.out.println("stones: " + stones.length);
        }

        return stones.length;
    }

    private Long[] change(long s) {
        if (s == 0) {
            return new Long[]{1L};
        } else {
            var string = String.valueOf(s);
            if (string.length() % 2 == 0) {
                var size2 = string.length() / 2;
                var s1 = parseLong(string.substring(0, size2));
                var s2 = parseLong(string.substring(size2));
                return new Long[]{s1, s2};
            } else {
                return new Long[]{s * 2024};
            }
        }
    }

    @Override
    public Integer solve2(Long[] parsedInput) {
        return blink(parsedInput, 75);
    }

}
