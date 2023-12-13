package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class Day11 implements ParserSolver<List<String>, Integer> {

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
    public Integer solve(List<String> parsedInput) {
        var expanded = expand(parsedInput);

        var galaxies = findGalaxies(expanded);

        galaxies.forEach(System.out::println);

        var pairs = generatePairs(galaxies);

        return pairs.stream()
                .map(Pair::distance)
                .reduce(0, Integer::sum);
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

    private char[][] expand(List<String> parsedInput) {
        var result = new ArrayList<String>();
        for (String s : parsedInput) {
            result.add(s);
            if (!s.contains("#")) {
                result.add(s);
            }
        }

        var result2 = new ArrayList<StringBuilder>();
        int len = result.get(0).length();

        for (int i = 0; i < len; i++) {
            var shouldAdd = !hasGalaxy(result, i);

            for (int j = 0; j < result.size(); j++) {
                result2.add(new StringBuilder());
            }

            for (int j = 0; j < result.size(); j++) {
                result2.get(j).append(result.get(j).charAt(i));

                if (shouldAdd) {
                    result2.get(j).append('.');
                }
            }
        }

        return result2.stream()
                .map(StringBuilder::toString)
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private static boolean hasGalaxy(List<String> result, int i) {
        return result.stream()
                .map(s -> s.charAt(i))
                .anyMatch(c -> c == '#');
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return 0;
    }

    private record Galaxy(int x, int y) {
    }

    private record Pair(Galaxy g1, Galaxy g2) {
        int distance() {
            return abs(g1.x() - g2.x()) + abs(g1.y() - g2.y());
        }
    }
}
