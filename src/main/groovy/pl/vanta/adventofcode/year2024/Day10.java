package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static pl.vanta.adventofcode.Utils.inBounds;

public class Day10 implements ParserSolver<char[][], Integer> {
    private static final char ZERO = '0';
    private static final char NINE = '9';

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
                .mapToInt(zero -> countScore(parsedInput, zero, ZERO))
                .sum();
    }

    private int countScore(char[][] array, Location location, int current) {
        if (current == NINE) {
            return 1;
        }

        var i = current + 1;
        return location.neighbours()
                .stream()
                .filter(l -> inBounds(l, array.length, array[0].length))
                .filter(l -> array[l.x()][l.y()] == i)
                .map(l -> countScore(array, l, i))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Location> findZeros(char[][] parsedInput) {
        var zeros = new ArrayList<Location>();
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == ZERO) {
                    zeros.add(new Location(i, j));
                }
            }
        }
        return zeros;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {

        return -1;

    }

}
