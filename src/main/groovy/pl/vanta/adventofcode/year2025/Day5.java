package pl.vanta.adventofcode.year2025;

import java.util.Set;

import org.apache.commons.lang3.LongRange;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.LongStream.range;

public class Day5 extends BaseDay<Day5.Input, Long> {
    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public Day5.Input parse(String input) {
        var lines = input.lines()
                .map(String::trim)
                .toList();

        var blankIndex = lines.indexOf("");

        var ranges = lines.subList(0, blankIndex).stream()
                .map(s -> s.split("-"))
                .map(a -> LongRange.of(parseLong(a[0]), parseLong(a[1])))
                .collect(toSet());

        var ids = lines.subList(blankIndex + 1, lines.size())
                .stream()
                .map(Long::parseLong)
                .collect(toSet());

        return new Input(ranges, ids);
    }

    @Override
    public Long solve(Day5.Input parsedInput) {
        return parsedInput.ids.stream()
                .filter(id -> parsedInput.ranges.stream().anyMatch(r -> r.contains(id)))
                .count();
    }

    @Override
    public Long solve2(Day5.Input parsedInput) {
        return parsedInput.ranges.stream()
                .flatMap(r -> range(r.getMinimum(), r.getMaximum() + 1).boxed())
                .distinct()
                .count();
    }

    public record Input(Set<LongRange> ranges, Set<Long> ids) {
    }
}
