package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
        var step = step(input, start, Direction.from('>'), path, 0);

        print(input, path);

        return step;
    }

    private void print(char[][] input, List<Location> path) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (path.contains(new Location(i, j))) {
                    System.out.print("@");
                } else {
                    System.out.print(input[i][j]);
                }
            }
            System.out.println();
        }
    }

    private int step(char[][] input, Location current, Direction direction, ArrayList<Location> path, int cost) {
        if (input[current.x()][current.y()] == WALL || path.contains(current)) {
            return -1;
        }

        path.add(current);

        if (input[current.x()][current.y()] == END) {
            return cost;
        }

        var posAhead = current.move(direction);
        var posLeft = current.move(direction.turnLeft());
        var posRight = current.move(direction.turnRight());

        var r1 = step(input, posAhead, direction, path, cost + 1);
        var r2 = step(input, posLeft, direction.turnLeft(), new ArrayList<>(path), cost + 1001);
        var r3 = step(input, posRight, direction.turnRight(), new ArrayList<>(path), cost + 1001);

        return Stream.of(r1, r2, r3)
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
