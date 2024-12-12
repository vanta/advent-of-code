package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Set<Location> visited = new HashSet<>();
        Set<Region> regions = new HashSet<>();

        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                var l = new Location(i, j);

                if (visited.add(l)) {
                    var r = new Region(parsedInput[i][j]);
                    check(l, r, parsedInput);
                    visited.addAll(r.plots);
                    regions.add(r);
                }
            }
        }

        return regions.stream()
                .map(Region::price)
                .reduce(0, Integer::sum);
    }

    private void check(Location location, Region region, char[][] parsedInput) {
        var sizeX = parsedInput.length;
        var sizeY = parsedInput[0].length;

        region.plots.add(location);

        location.neighbours().stream()
                .filter(l -> inBounds(l, sizeX, sizeY))
                .filter(l -> region.letter == parsedInput[l.x()][l.y()])
                .filter(not(region.plots::contains))
                .forEach(ln -> check(ln, region, parsedInput));
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return -1;
    }

    private record Region(char letter, Set<Location> plots, Set<Location> border) {
        private Region(char letter) {
            this(letter, new HashSet<>(), new HashSet<>());
        }

        int price() {
            return plots.size() * border.size();
        }
    }

}
