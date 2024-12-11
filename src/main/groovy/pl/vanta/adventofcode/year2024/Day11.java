package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.concurrent.Memoizer;
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
        return blink(parsedInput, 25);
    }

    private int blink(String[] parsedInput, int blinks) {
        var stones = parsedInput;

        var map = new HashMap<String, String[]>();

        for (int i = 0; i < blinks; i++) {
            System.out.print("Blink " + i + ", ");
            stones = Arrays.stream(stones)
//                    .parallel()
                    .map(s -> map.computeIfAbsent(s, this::change))
                    .flatMap(Arrays::stream)
                    .toArray(String[]::new);

            System.out.println("stones: " + stones.length);
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
        return blink(parsedInput, 75);
    }

}
