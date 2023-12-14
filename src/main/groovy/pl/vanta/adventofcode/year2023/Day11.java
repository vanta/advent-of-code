package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

public class Day11 implements ParserSolver<List<String>, Long> {

    @Override
    public int getDayNumber() {
        return 11;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .toList();
    }

    @Override
    public Long solve(List<String> parsedInput) {
        return solveCommon(parsedInput, 2);
    }

    @Override
    public Long solve2(List<String> parsedInput) {
        return solveCommon(parsedInput, 1000000);
    }

    private long solveCommon(List<String> parsedInput, int expandRatio) {
        var input = parsedInput.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);

        var galaxies = findGalaxies(input);
        var emptyRows = findEmptyRows(galaxies, input.length);
        var emptyCols = findEmptyCols(galaxies, input[0].length);

        var shiftY = new HashMap<Galaxy, Integer>();
        for (int emptyCol : emptyCols) {
            galaxies.stream()
                    .filter(g -> g.y() > emptyCol)
                    .forEach(g ->
                            shiftY.compute(g, (galaxy, shift) -> expandRatio - 1 + (shift == null ? 0 : shift))
                    );
        }

        var shiftX = new HashMap<Galaxy, Integer>();
        for (int emptyRow : emptyRows) {
            galaxies.stream()
                    .filter(g -> g.x() > emptyRow)
                    .forEach(g ->
                            shiftX.compute(g, (galaxy, shift) -> expandRatio - 1 + (shift == null ? 0 : shift))
                    );
        }

        galaxies = galaxies.stream()
                .map(g -> g.add(shiftX.getOrDefault(g, 0), shiftY.getOrDefault(g, 0)))
                .toList();

        var pairs = generatePairs(galaxies);

        return pairs.stream()
                .map(Pair::distance)
                .reduce(0L, Long::sum);
    }

    private List<Galaxy> findGalaxies(char[][] expanded) {
        List<Galaxy> galaxies = new ArrayList<>();
        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[i].length; j++) {
                if (expanded[i][j] == '#') {
                    galaxies.add(new Galaxy(i, j));
                }
            }
        }

        return galaxies;
    }

    private List<Integer> findEmptyRows(List<Galaxy> galaxies, int length) {
        var notEmptyRows = galaxies.stream()
                .map(Galaxy::x)
                .collect(toSet());

        return filter(length, notEmptyRows);
    }

    private List<Integer> findEmptyCols(List<Galaxy> galaxies, int length) {
        var notEmptyCols = galaxies.stream()
                .map(Galaxy::y)
                .collect(toSet());

        return filter(length, notEmptyCols);
    }

    private static List<Integer> filter(int length, Set<Integer> notEmpty) {
        return range(0, length)
                .filter(i -> !notEmpty.contains(i))
                .boxed()
                .toList();
    }

    private Set<Pair> generatePairs(List<Galaxy> galaxies) {
        var result = new HashSet<Pair>();
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                result.add(new Pair(galaxies.get(i), galaxies.get(j)));
            }
        }
        return result;
    }

    private record Galaxy(int x, int y) {
        Galaxy add(int x, int y) {
            return new Galaxy(this.x + x, this.y + y);
        }
    }

    private record Pair(Galaxy g1, Galaxy g2) {
        long distance() {
            return abs(g1.x() - g2.x()) + abs(g1.y() - g2.y());
        }
    }
}
