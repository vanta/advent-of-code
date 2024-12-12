package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.function.Predicate.not;
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
        Set<Region> regions = new HashSet<>();

        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                processPlot(parsedInput, visited, regions, new Location(i, j));
            }
        }
        return regions;
    }

    private void processPlot(char[][] parsedInput, Set<Location> visited, Set<Region> regions, Location location) {
        if (visited.add(location)) {
            var r = new Region(parsedInput[location.x()][location.y()]);
            check(location, r, parsedInput);
            visited.addAll(r.plots);
            regions.add(r);
        }
    }

    private void check(Location location, Region region, char[][] parsedInput) {
        var sizeX = parsedInput.length;
        var sizeY = parsedInput[0].length;

        region.plots.add(location);

        location.neighbours().stream()
                .peek(l -> {
                    if (!inBounds(l, sizeX, sizeY) || parsedInput[l.x()][l.y()] != region.letter) {
                        region.borders.incrementAndGet();
                    }
                })
                .filter(l -> inBounds(l, sizeX, sizeY))
                .filter(l -> region.letter == parsedInput[l.x()][l.y()])
                .filter(not(region.plots::contains))
                .forEach(ln -> check(ln, region, parsedInput));
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return -1;
    }

    private record Region(char letter, Set<Location> plots, AtomicInteger borders) {
        private Region(char letter) {
            this(letter, new HashSet<>(), new AtomicInteger(0));
        }

        int price() {
            return plots.size() * borders.get();
        }
    }

}
