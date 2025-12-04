package pl.vanta.adventofcode.year2025;

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
        var result = new java.util.ArrayList<Long>();

        for (long i = range.getMinimum(); i <= range.getMaximum(); i++) {
            var s = String.valueOf(i);
            if (s.length() % 2 == 0) {
                var h1 = s.substring(0, s.length() / 2);
                var h2 = s.substring(s.length() / 2);
                if(h1.equals(h2)){
                    result.add(i);
                }
            }
        }

        return result;
    }

    @Override
    public Long solve2(List<LongRange> parsedInput) {

        return 1L;
    }
}
