package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;

import pl.vanta.adventofcode.Direction;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

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
        step(input, start, Direction.from('>'), path);

        return -1;
    }

    private void step(char[][] input, Location current, Direction direction, ArrayList<Location> path) {
        if(input[current.x()][current.y()] == WALL) {
            return;
        }

        if(path.contains(current)) {
            return;
        }

        path.add(current);

        if(input[current.x()][current.y()] == END) {
            return;
        }

        current.neighbours().stream()
                .filter(n -> input[n.x()][n.y()] != WALL)
                .filter(n -> !path.contains(n))
                .forEach(n -> step(input, n, direction, path));

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
