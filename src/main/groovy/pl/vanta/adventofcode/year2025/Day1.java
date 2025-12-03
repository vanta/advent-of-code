package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day1 extends BaseDay<List<Integer>, Integer, Integer> {
    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<Integer> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(s -> (s.charAt(0) == 'L' ? -1 : 1) * parseInt(s.substring(1)))
                .toList();
    }

    @Override
    public Integer solve(List<Integer> parsedInput) {
        var value = 50;
        var zeros = 0;

        for (Integer move : parsedInput) {
            value = (value + move) % 100;

            if (value == 0) {
                zeros++;
            }
        }

        return zeros;
    }

    @Override
    public Integer solve2(List<Integer> parsedInput) {
        var value = 50;
        var zeros = 0;

        for (Integer move : parsedInput) {

        }

        return zeros;

    }

}
