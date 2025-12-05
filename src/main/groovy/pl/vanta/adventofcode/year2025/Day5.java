package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.LongRange;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toSet;

public class Day5 extends BaseDay<Day5.Input, Integer> {
    @Override
    public int getDayNumber() {
        return 5;
    }

    @Override
    public Day5.Input parse(String input) {
        List<String> lines = input.lines()
                .map(String::trim)
                .toList();

        int blankIndex = lines.indexOf("");

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
    public Integer solve(Day5.Input parsedInput) {
        return (int) parsedInput.ids.stream()
                .filter(id -> parsedInput.ranges.stream().anyMatch(r -> r.contains(id)))
                .count();
    }

    @Override
    public Integer solve2(Day5.Input parsedInput) {

        return 0;
    }

    public record Input(Set<LongRange> ranges, Set<Long> ids) {
    }
}
