package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

import static pl.vanta.adventofcode.Utils.red;

public class Day10 implements ParserSolver<char[][], Integer> {

    private static final char START = 'S';

    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        var start = findStart(parsedInput);

        printMap(parsedInput);

        return traverse(parsedInput, start);
    }

    @Override
    public Integer solve2(char[][] parsedInput) {

        return 1111;
    }

    private int traverse(char[][] map, Point start) {
        var prev = start;
        var curr = start(map, start);

        var path = new ArrayList<>();
        path.add(start);

        while (!Objects.equals(curr, start)) {
            path.add(curr);

            var next = findNext(map, curr, prev);
            prev = curr;
            curr = next;
        }

        return path.size() / 2;
    }

    private Point findNext(char[][] map, Point c, Point prev) {
        return switch (map[c.x][c.y]) {
            case '|' -> choose(new Point(c.x() - 1, c.y()), new Point(c.x() + 1, c.y()), prev);
            case '-' -> choose(new Point(c.x(), c.y() - 1), new Point(c.x(), c.y() + 1), prev);
            case 'F' -> choose(new Point(c.x() + 1, c.y()), new Point(c.x(), c.y() + 1), prev);
            case '7' -> choose(new Point(c.x() + 1, c.y()), new Point(c.x(), c.y() - 1), prev);
            case 'J' -> choose(new Point(c.x() - 1, c.y()), new Point(c.x(), c.y() - 1), prev);
            case 'L' -> choose(new Point(c.x() - 1, c.y()), new Point(c.x(), c.y() + 1), prev);
            default -> throw new RuntimeException("Unknown char: " + map[c.x()][c.y()]);
        };
    }

    private static Point start(char[][] map, Point start) {
        if ("J|L".contains("" + map[start.x + 1][start.y])) {
            return new Point(start.x + 1, start.y);
        }
        if ("7|F".contains("" + map[start.x - 1][start.y])) {
            return new Point(start.x - 1, start.y);
        }
        if ("7-J".contains("" + map[start.x][start.y + 1])) {
            return new Point(start.x, start.y + 1);
        }
        if ("F-L".contains("" + map[start.x][start.y - 1])) {
            return new Point(start.x, start.y - 1);
        }

        return null;
    }

    private Point choose(Point p1, Point p2, Point prev) {
        return Objects.equals(p1, prev) ? p2 : p1;
    }

    private static void printMap(char[][] parsedInput) {
        for (char[] chars : parsedInput) {
            for (char c : chars) {
                if (c == START) {
                    System.out.print(red(c));
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    private Point findStart(char[][] parsedInput) {
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == START) {
                    return new Point(i, j);
                }
            }
        }

        throw new RuntimeException("No start found");
    }

    private record Point(int x, int y) {
    }
}
