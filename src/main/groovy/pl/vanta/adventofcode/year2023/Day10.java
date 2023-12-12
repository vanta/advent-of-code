package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static pl.vanta.adventofcode.Utils.red;

public class Day10 implements ParserSolver<char[][], Integer> {

    private static final char START = 'S';

    private static final Map<Character, Character> MAPPINGS = Map.of(
            'J', '┛',
            '7', '┓',
            'F', '┏',
            'L', '┗',
            '|', '┃',
            '-', '━'
    );

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

        return traverse(parsedInput, start).size() / 2;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        var start = findStart(parsedInput);
        var path = traverse(parsedInput, start);

        paint(path, parsedInput);

        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            var before = new String(parsedInput[i]);
            var tmp = before
                    .replaceAll("LJ", "||")
                    .replaceAll("F7", "||")
                    .replaceAll("L7", "|.")
                    .replaceAll("FJ", "|.")
                    .replaceAll("\\|\\|", "..")
                    ;

            System.out.println(before);
            System.out.println(tmp);


            if (tmp.isEmpty()) {
                continue;
            }

            boolean inLoop = false;

            for (int j = 0; j < tmp.toCharArray().length; j++) {
                if (path.contains(new Point(i, j))) {
                    inLoop = !inLoop;
                } else {
                    if (inLoop) {
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    private void paint(List<Point> path, char[][] map) {
        for (int i = 0; i < map.length; i++) {
           for(int j = 0; j < map[0].length; j++) {
               if (path.contains(new Point(i, j))) {
                   System.out.print(red(MAPPINGS.getOrDefault(map[i][j], map[i][j])));
               } else {
                   System.out.print(".");
               }
           }
            System.out.println();
        }
    }

    private List<Point> traverse(char[][] map, Point start) {
        var prev = start;
        var curr = afterStart(map, start);

        var path = new ArrayList<Point>();
        path.add(start);

        while (!Objects.equals(curr, start)) {
            path.add(curr);

            var next = findNext(map, curr, prev);
            prev = curr;
            curr = next;
        }

        return path;
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

    private static Point afterStart(char[][] map, Point start) {
        var directions = Map.of(
                "J|L", start.down(),
                "7|F", start.up(),
                "7-J", start.right(),
                "F-L", start.left()
        );

        return directions.entrySet().stream()
                .filter(e -> isWithinArray(e.getValue().x, e.getValue().y, map))
                .filter(e -> e.getKey().indexOf(map[e.getValue().x()][e.getValue().y()]) >= 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown char: " + map[start.x()][start.y()]))
                .getValue();
    }

    private static boolean isWithinArray(int x, int y, char[][] map) {
        return x >= 0 && y >= 0 && x < map.length && y < map[0].length;
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
        Point minus(Point p) {
            return new Point(x - p.x, y - p.y);
        }

        Point down() {
            return new Point(x + 1, y);
        }

        Point up() {
            return new Point(x - 1, y);
        }

        Point left() {
            return new Point(x, y - 1);
        }

        Point right() {
            return new Point(x, y + 1);
        }
    }
}
