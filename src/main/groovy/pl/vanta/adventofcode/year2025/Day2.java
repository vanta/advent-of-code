package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.LongRange;

import static java.lang.Long.parseLong;

public class Day2 extends BaseDay<List<LongRange>, Long> {
    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public List<LongRange> parse(String lines) {
        return Stream.of(lines.trim().split(","))
                .map(s -> s.split("-"))
                .map(a -> LongRange.of(parseLong(a[0]), parseLong(a[1])))
                .toList();
    }

    @Override
    public Long solve(List<LongRange> parsedInput) {
        return parsedInput.stream()
                .map(this::findRepeats)
                .flatMap(List::stream)
                .reduce(0L, Long::sum);
    }

    private List<Long> findRepeats(LongRange range) {
        var result = new ArrayList<Long>();

        for (long i = range.getMinimum(); i <= range.getMaximum(); i++) {
            if (repeats(i)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean repeats(Long number) {
        var s = String.valueOf(number);
        if (s.length() % 2 == 0) {
            return s.substring(0, s.length() / 2).equals(s.substring(s.length() / 2));
        }
        return false;
    }

    @Override
    public Long solve2(List<LongRange> parsedInput) {

        return 1L;
    }
}
