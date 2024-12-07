package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static pl.vanta.adventofcode.year2024.Day6.Direction.UP;

public class Day6 implements ParserSolver<char[][], Integer> {

    private static final char OBSTACLE = '#';

    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public char[][] parse(String lines) {
        return stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return (int) path(findStart(parsedInput), parsedInput)
                .stream()
                .map(p -> Pair.of(p.x, p.y))
                .distinct()
                .count();
    }

    private List<Position> path(Position start, char[][] array) {
        var path = new ArrayList<Position>();
        var position = start;

        do {
            path.add(position);
        } while ((position = getNextPos(position, array)) != null);

        return path;
    }

    private Position getNextPos(Position current, char[][] array) {
        var nextPos = doStep(current);
        var outside = nextPos.x < 0 || nextPos.x >= array.length || nextPos.y < 0 || nextPos.y >= array[nextPos.x].length;

        if (outside) {
            return null;
        }

        if (array[nextPos.x][nextPos.y] == OBSTACLE) {
//            return doStep(new Position(current.x, current.y, current.direction.turn()));
            return getNextPos(new Position(current.x, current.y, current.direction.turn()), array);
        } else {
            return nextPos;
        }
    }

    private Position doStep(Position current) {
        return switch (current.direction) {
            case UP -> new Position(current.x - 1, current.y, current.direction);
            case DOWN -> new Position(current.x + 1, current.y, current.direction);
            case LEFT -> new Position(current.x, current.y - 1, current.direction);
            case RIGHT -> new Position(current.x, current.y + 1, current.direction);
        };
    }

    private Position findStart(char[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^') {
                    return new Position(i, j, UP);
                }
            }
        }

        return null;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        var path = path(findStart(parsedInput), parsedInput);

        int loops = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Position pos = path.get(i);
            Position next = path.get(i + 1);

            parsedInput[next.x][next.y] = OBSTACLE;

            if (hasLoop(pos, parsedInput)) {
                loops++;
            }

            parsedInput[next.x][next.y] = '.';
        }

        return loops;
    }

    private boolean hasLoop(Position start, char[][] array) {
        printArray(start, array);
        System.out.println("-----------------------");
        var position = start;

        while ((position = getNextPos(position, array)) != null) {
            if (position.equals(start)) {
                return true;
            }
        }

        return false;
    }

    private void printArray(Position start, char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == start.x && j == start.y) {
                    System.out.print(start.direction.toChar());
                } else {
                    System.out.print(array[i][j]);
                }
            }
            System.out.println();
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        Direction turn() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }

        char toChar() {
            return switch (this) {
                case UP -> '^';
                case DOWN -> 'v';
                case LEFT -> '<';
                case RIGHT -> '>';
            };
        }
    }

    private record Position(int x, int y, Direction direction) {
    }

}
