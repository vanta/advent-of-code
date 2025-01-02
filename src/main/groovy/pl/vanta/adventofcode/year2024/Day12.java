package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;
import static java.util.stream.IntStream.range;
import static pl.vanta.adventofcode.Utils.inBounds;

public class Day12 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public char[][] parse(String lines) {
        return stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return getRegions(parsedInput)
                .map(Region::price)
                .reduce(0, Integer::sum);
    }

    private Stream<Region> getRegions(char[][] parsedInput) {
        Set<Location> visited = new HashSet<>();

        return range(0, parsedInput.length * parsedInput[0].length)
                .mapToObj(i -> new Location(i / parsedInput.length, i % parsedInput.length))
                .filter(not(visited::contains))
                .map(l -> check(l, new Region(parsedInput[l.x()][l.y()], new ArrayList<>()), parsedInput, visited));
    }

    private Region check(Location location, Region region, char[][] parsedInput, Set<Location> visited) {
        visited.add(location);
        region.plots.add(location);

        location.neighbours().stream()
                .filter(l -> inBounds(l, parsedInput.length, parsedInput[0].length))
                .filter(l -> parsedInput[l.x()][l.y()] == region.symbol)
                .filter(not(visited::contains))
                .forEach(l -> check(l, region, parsedInput, visited));

        return region;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return getRegions(parsedInput)
                .sorted(comparing(Region::symbol))
                .peek(r -> System.out.println("Checking region: " + r.symbol))
                .map(Region::price2)
                .peek(p -> System.out.println("Price: " + p))
                .reduce(0, Integer::sum);
    }

    private record Region(char symbol, List<Location> plots) {
        int price() {
            var perimeter = plots.stream()
                    .map(this::neighboursCount)
                    .map(i -> 4 - i)
                    .reduce(0, Integer::sum);

            return perimeter * plots.size();
        }

        int price2() {
            var corners = plots.stream()
                    .map(this::cornersCount)
                    .reduce(0, Integer::sum);

            return corners * plots.size();
        }

        private int neighboursCount(Location l) {
            return (int) l.neighbours().stream().filter(plots::contains).count();
        }

        private int cornersCount(Location l) {
            var pairs = List.of(
                    Pair.of(l.left(), l.up()),
                    Pair.of(l.up(), l.right()),
                    Pair.of(l.right(), l.down()),
                    Pair.of(l.down(), l.left())
            );

            var corners = (int) pairs.stream()
                    .filter(p ->
                            !plots.contains(p.getLeft()) && !plots.contains(p.getRight())
                                    || plots.contains(p.getLeft()) && plots.contains(p.getRight()) && !plots.contains(diag(l, p.getLeft(), p.getRight()))
                    )
                    .count();

            return corners;
        }

        private Location diag(Location location, Location left, Location right) {
            var o1 = location.offset(left);
            var o2 = location.offset(right);

            return location.move(o1.add(o2));
        }
    }
}
