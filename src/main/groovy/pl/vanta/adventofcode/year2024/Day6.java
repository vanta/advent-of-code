package pl.vanta.adventofcode.year2024;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;

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
        return path(findStart(parsedInput), parsedInput).size();
    }

    private Set<Pair<Integer, Integer>> path(Pair<Integer, Integer> start, char[][] array) {
        var outside = false;
        var path = new HashSet<Pair<Integer, Integer>>();
        var position = start;
        var dir = Direction.UP;

        do {
//            print(position, dir, array);
//            System.out.printf("Path size=%d\n", path.size());
//            System.out.println("------------------------------");
            path.add(position);

            var maybeNewPos = doStep(position, dir);
            outside = maybeNewPos.getLeft() < 0 || maybeNewPos.getLeft() >= array.length || maybeNewPos.getRight() < 0 || maybeNewPos.getRight() >= array[maybeNewPos.getLeft()].length;

            if(!outside) {
                if (array[maybeNewPos.getLeft()][maybeNewPos.getRight()] == '#') {
                    dir = dir.next();
                    position = doStep(position, dir);
                } else {
                    position = maybeNewPos;
                }
            }
        } while(!outside);

        return path;
    }

    private void print(Pair<Integer, Integer> start, Direction dir, char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == start.getLeft() && j == start.getRight()) {
                    System.out.print(dir.getSign());
                } else {
                    System.out.print(array[i][j]);
                }
            }
            System.out.println();
        }
    }

    private Pair<Integer, Integer> doStep(Pair<Integer, Integer> current, Direction dir) {
        return switch (dir) {
            case UP -> Pair.of(current.getLeft() - 1, current.getRight());
            case DOWN -> Pair.of(current.getLeft() + 1, current.getRight());
            case LEFT -> Pair.of(current.getLeft(), current.getRight() - 1);
            case RIGHT -> Pair.of(current.getLeft(), current.getRight() + 1);
        };
    }

    private Pair<Integer, Integer> findStart(char[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^') {
                    return Pair.of(i, j);
                }
            }
        }

        return null;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;
    }

    enum Direction {
        UP('^'), DOWN('v'), LEFT('<'), RIGHT('>');

        private final char sign;

        Direction(char sign) {
            this.sign = sign;
        }

        Direction next() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }

        public char getSign() {
            return sign;
        }
    }

}
