package pl.vanta.adventofcode.year2024;

import java.util.Arrays;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Long.parseLong;

public class Day11 implements ParserSolver<String[], Integer> {
    @Override
    public int getDayNumber() {
        return 11;
    }

    @Override
    public String[] parse(String lines) {
        return lines.replace("\n", "").trim().split(" ");
    }

    @Override
    public Integer solve(String[] parsedInput) {
        var blinks = 25;
        var stones = parsedInput;

        for (int i = 0; i < blinks; i++) {
            stones = Arrays.stream(stones)
                    .map(this::change)
                    .flatMap(Arrays::stream)
                    .toArray(String[]::new);
        }

        return stones.length;
    }

    private String[] change(String s) {
        if ("0".equals(s)) {
            return new String[]{"1"};
        } else if (s.length() % 2 == 0) {
            var size2 = s.length() / 2;
            var s1 = parseLong(s.substring(0, size2));
            var s2 = parseLong(s.substring(size2));
            return new String[]{String.valueOf(s1), String.valueOf(s2)};
        } else {
            return new String[]{String.valueOf(parseLong(s) * 2024)};
        }
    }

    @Override
    public Integer solve2(String[] parsedInput) {
        return -1;
    }

}
