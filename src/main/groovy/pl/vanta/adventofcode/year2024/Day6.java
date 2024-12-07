package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static pl.vanta.adventofcode.year2024.Day6.Direction.*;

public class Day6 implements ParserSolver<char[][], Integer> {

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
        return (int)path(findStart(parsedInput), parsedInput)
                .stream()
                .map(t -> Pair.of(t.getLeft(), t.getMiddle()))
                .distinct()
                .count();
    }

    private List<Triple<Integer, Integer, Direction>> path(Triple<Integer, Integer, Direction> start, char[][] array) {
        var path = new ArrayList<Triple<Integer, Integer, Direction>>();
        var position = start;

        do {
            path.add(position);

        } while ((position = getNextPos(position, array)) != null);

        return path;
    }

    private Triple<Integer, Integer, Direction> getNextPos(Triple<Integer, Integer, Direction> current, char[][] array) {
        var nextPos = doStep(current);
        var outside = nextPos.getLeft() < 0 || nextPos.getLeft() >= array.length || nextPos.getMiddle() < 0 || nextPos.getMiddle() >= array[nextPos.getLeft()].length;

        if(outside) {
            return null;
        }

        if(array[nextPos.getLeft()][nextPos.getMiddle()] == '#') {
            return doStep(Triple.of(current.getLeft(), current.getMiddle(), current.getRight().turn()));
        } else {
            return nextPos;
        }
    }

    private Triple<Integer, Integer, Direction> doStep(Triple<Integer, Integer, Direction> current) {
        return switch (current.getRight()) {
            case UP -> Triple.of(current.getLeft() - 1, current.getMiddle(), current.getRight());
            case DOWN -> Triple.of(current.getLeft() + 1, current.getMiddle(), current.getRight());
            case LEFT -> Triple.of(current.getLeft(), current.getMiddle() - 1, current.getRight());
            case RIGHT -> Triple.of(current.getLeft(), current.getMiddle() + 1, current.getRight());
        };
    }

    private Triple<Integer, Integer, Direction> findStart(char[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^') {
                    return Triple.of(i, j, UP);
                }
            }
        }

        return null;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        var path = path(findStart(parsedInput), parsedInput);

        int loops = 0;
        for (Triple<Integer, Integer, Direction> place : path) {

        }

        return loops;
    }

    enum Direction {
        UP('^'), DOWN('v'), LEFT('<'), RIGHT('>');

        final char sign;

        Direction(char sign) {
            this.sign = sign;
        }

        Direction turn() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }
    }

}
