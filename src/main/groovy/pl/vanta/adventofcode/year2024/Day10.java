package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Character.*;
import static java.util.stream.Collectors.toSet;
import static pl.vanta.adventofcode.Utils.inBounds;

public class Day10 implements ParserSolver<char[][], Integer> {
    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public char[][] parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return findZeros(parsedInput).stream()
                .map(zero -> countScore(parsedInput, zero, 0))
                .map(Set::size)
                .reduce(0, Integer::sum);
    }

    private Set<Location> countScore(char[][] array, Location location, int current) {
        if (current == 9) {
            return Set.of(location);
        }

        var next = current + 1;
        return location.neighbours()
                .stream()
                .filter(l -> inBounds(l, array.length, array[0].length))
                .filter(l -> getNumericValue(array[l.x()][l.y()]) == next)
                .flatMap(l -> countScore(array, l, next).stream())
                .collect(toSet());
    }

    private List<Location> findZeros(char[][] parsedInput) {
        var zeros = new ArrayList<Location>();
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == '0') {
                    zeros.add(new Location(i, j));
                }
            }
        }
        return zeros;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return findZeros(parsedInput).stream()
                .map(zero -> doRate(parsedInput, zero, (char) 0))
                .reduce(0, Integer::sum);
    }

    private int doRate(char[][] array, Location location, int current) {
        if (current == 9) {
            return 1;
        }

        var next = current + 1;

        return location.neighbours()
                .stream()
                .filter(l -> inBounds(l, array.length, array[0].length))
                .filter(l -> getNumericValue(array[l.x()][l.y()]) == next)
                .mapToInt(l -> doRate(array, l, next))
                .sum();
    }

}
