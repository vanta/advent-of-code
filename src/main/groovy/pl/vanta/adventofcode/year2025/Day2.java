package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import groovy.lang.IntRange;
import org.apache.commons.lang3.LongRange;

import static java.lang.Integer.parseInt;
import static java.lang.Long.*;

public class Day2 extends BaseDay<List<LongRange>, Long> {
    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public List<LongRange> parse(String lines) {
        return Stream.of(lines.split(","))
                .map(s -> s.split("-"))
                .map(a -> LongRange.of(parseLong(a[0]), parseLong(a[1])))
                .toList();
    }

    @Override
    public Long solve(List<LongRange> parsedInput) {


        return 1L;
    }

    @Override
    public Long solve2(List<LongRange> parsedInput) {

        return 1L;
    }
}
