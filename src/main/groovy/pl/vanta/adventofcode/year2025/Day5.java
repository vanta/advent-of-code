package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Gatherers;

import org.apache.commons.lang3.LongRange;
import org.apache.commons.lang3.Range;

import static java.lang.Long.parseLong;
import static java.lang.Math.*;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toSet;

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
                .sorted(comparingLong(Range::getMinimum))
                .toList();

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
        var currentRanges = new ArrayList<LongRange>();
        var newRanges = new ArrayList<>(parsedInput.ranges);
        boolean merged;

        do {
            currentRanges = new ArrayList<>(newRanges);
            newRanges.clear();
            for(int i = 0; i < currentRanges.size(); i++) {
                merged = false;
                for(int j = i + 1; j < currentRanges.size(); j++) {
                    if(overlap(currentRanges.get(i), currentRanges.get(j))) {
                        newRanges.add(merge(currentRanges.get(i), currentRanges.get(j)));
                        merged = true;
                        i += 1;
                        break;
                    }
                }
                if(!merged) {
                    newRanges.add(currentRanges.get(i));
                }
            }
        } while (currentRanges.size() != newRanges.size());

        return currentRanges.stream()
                .map(r -> r.getMaximum() - r.getMinimum() + 1)
                .reduce(0L, Long::sum);
    }

    private boolean overlap(LongRange r1, LongRange r2) {
        return r1.getMaximum() >= r2.getMinimum() && r2.getMaximum() >= r1.getMinimum();
    }

    private LongRange merge(LongRange r1, LongRange r2) {
        return LongRange.of(min(r1.getMinimum(), r2.getMinimum()), max(r1.getMaximum(), r2.getMaximum()));
    }

    public record Input(List<LongRange> ranges, Set<Long> ids) {
    }
}
