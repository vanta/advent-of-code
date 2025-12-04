package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import groovy.lang.IntRange;

import static java.lang.Integer.parseInt;

public class Day2 extends BaseDay<List<IntRange>, Integer> {
    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public List<IntRange> parse(String lines) {
        return Stream.of(lines.split(","))
                .map(s -> s.split("-"))
                .map(a -> new IntRange(parseInt(a[0]), parseInt(a[1])))
                .toList();
    }

    @Override
    public Integer solve(List<IntRange> parsedInput) {


        return 1;
    }

    @Override
    public Integer solve2(List<IntRange> parsedInput) {

        return 1;
    }
}
