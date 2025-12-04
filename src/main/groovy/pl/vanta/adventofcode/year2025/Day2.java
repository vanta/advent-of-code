package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.LongRange;

import static java.lang.Long.parseLong;
import static java.util.stream.LongStream.range;
import static org.apache.commons.lang3.StringUtils.repeat;

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
        return range(range.getMinimum(), range.getMaximum() + 1)
                .mapToObj(String::valueOf)
                .filter(s -> s.length() % 2 == 0)
                .filter(s -> s.substring(0, s.length() / 2).equals(s.substring(s.length() / 2)))
                .map(Long::parseLong)
                .toList();
    }

    private List<Long> findRepeats2(LongRange range) {
        return range(range.getMinimum(), range.getMaximum() + 1)
                .filter(this::invalid2)
                .boxed()
                .toList();
    }

    private boolean invalid2(Long number) {
        var s = String.valueOf(number);

        for (int i = 1; i <= s.length() / 2; i++) {
            var start = s.substring(0, i);
            var len = s.length() / start.length();

            if (repeat(start, len).equals(s)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Long solve2(List<LongRange> parsedInput) {
        return parsedInput.stream()
                .map(this::findRepeats2)
                .flatMap(List::stream)
                .reduce(0L, Long::sum);
    }
}
