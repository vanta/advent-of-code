package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;

import pl.vanta.adventofcode.Direction;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Comparator.comparingInt;

public class Day16 implements ParserSolver<char[][], Integer> {
    private static final char WALL = '#';
    private static final char END = 'E';
    private static final char START = 'S';

    @Override
    public int getDayNumber() {
        return 16;
    }

    @Override
    public char[][] parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] input) {
        var start = findStart(input);

        var path = new ArrayList<Location>();
        var result = step(input, start, Direction.from('>'), path, 0);

        return result;
    }

    private int step(char[][] input, Location current, Direction direction, ArrayList<Location> path, int cost) {
        path.add(current);

        if (input[current.x()][current.y()] == END) {
            return cost;
        }

        return current.neighbours().stream()
                .filter(n -> input[n.x()][n.y()] != WALL)
                .filter(n -> !path.contains(n))
                .map(n -> step(input, n, direction, new ArrayList<>(path), cost + 1))
                .filter(l -> l > 0)
                .min(comparingInt(l -> l))
                .orElse(-1);
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return -1;
    }

    private Location findStart(char[][] parsedInput) {
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == START) {
                    return new Location(i, j);
                }
            }
        }

        throw new IllegalArgumentException("Not found");
    }
}
