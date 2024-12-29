package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Math.min;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static pl.vanta.adventofcode.Utils.inBounds;

public class Day12 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public char[][] parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return getRegions(parsedInput).stream()
                .map(Region::price)
                .reduce(0, Integer::sum);
    }

    private Set<Region> getRegions(char[][] parsedInput) {
        Set<Location> visited = new HashSet<>();

        return range(0, parsedInput.length * parsedInput[0].length)
                .mapToObj(i -> new Location(i / parsedInput.length, i % parsedInput.length))
                .filter(l -> !visited.contains(l))
                .map(l -> check(l, new Region(), parsedInput, visited))
                .collect(toSet());
    }

    private Region check(Location location, Region region, char[][] parsedInput, Set<Location> visited) {
        var letter = parsedInput[location.x()][location.y()];

        visited.add(location);
        region.plots.incrementAndGet();

        location.neighbours().forEach(l -> process(l, region, parsedInput, visited, letter));

        return region;
    }

    private void process(Location location, Region region, char[][] parsedInput, Set<Location> visited, char letter) {
        if (inBounds(location, parsedInput.length, parsedInput[0].length)) {
            if (parsedInput[location.x()][location.y()] == letter) {
                if (!visited.contains(location)) {
                    check(location, region, parsedInput, visited);
                }
            } else {
                region.borders.add(location);
            }
        } else {
            region.borders.add(location);
        }
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return getRegions(parsedInput).stream()
                .map(Region::price2)
                .reduce(0, Integer::sum);
    }

    private record Region(AtomicInteger plots, List<Location> borders) {
        private Region() {
            this(new AtomicInteger(0), new ArrayList<>());
        }

        int price() {
            return plots.get() * borders.size();
        }

        int price2() {
            var groupedByX = borders.stream()
                    .collect(groupingBy(Location::x));

            var xs = groupedByX.values().stream()
                    .map(locations -> locations.stream().map(Location::y).sorted().toList())
                    .map(this::groupAndCount)
                    .reduce(0, Integer::sum);


            var groupedByY = borders.stream()
                    .collect(groupingBy(Location::y));

            var ys = groupedByY.values().stream()
                    .map(locations -> locations.stream().map(Location::x).sorted().toList())
                    .map(this::groupAndCount)
                    .reduce(0, Integer::sum);


            return plots.get() * min(xs, ys);
        }

        int groupAndCount(List<Integer> numbers) {
            return range(0, numbers.size())
                    .boxed()
                    .collect(groupingBy(
                            i -> numbers.get(i) - i,
                            mapping(numbers::get, toList())
                    ))
                    .size();
        }
    }

}
