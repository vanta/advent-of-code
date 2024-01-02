package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Day21 implements ParserSolver<char[][], Long> {

    private static final int ITERATIONS = 26_501_365;

    @Override
    public int getDayNumber() {
        return 21;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Long solve(char[][] parsedInput) {
        var start = findStart(parsedInput);

        var places = findAllPoints(parsedInput, start, 64);

        return (long) places.size();
    }

    private Collection<Point> findAllPoints(char[][] parsedInput, Point start, int iterations) {
        var result = new HashSet<Point>();
        var seen = new HashSet<Point>();
        var queue = new LinkedList<Step>();

        seen.add(start);
        queue.add(new Step(start, iterations));

        while (!queue.isEmpty()) {
            var step = queue.poll();

            if (step.s % 2 == 0) {
                result.add(step.p);
            }
            if (step.s == 0) {
                continue;
            }

            Stream.of(
                            new Point(step.p.x - 1, step.p.y),
                            new Point(step.p.x + 1, step.p.y),
                            new Point(step.p.x, step.p.y - 1),
                            new Point(step.p.x, step.p.y + 1))
                    .filter(p -> p.x >= 0 && p.y >= 0 && p.x < parsedInput.length && p.y < parsedInput[p.x].length)
                    .filter(p -> parsedInput[p.x][p.y] != '#')
                    .filter(p -> !seen.contains(p))
                    .forEach(p -> {
                        seen.add(p);
                        queue.add(new Step(p, step.s - 1));
                    });
        }

        return result;
    }

    private Collection<Point> getNextStepPlaces(Collection<Point> places, char[][] parsedInput) {
        return places.stream()
                .flatMap(point -> getNeighbourPlaces(point, parsedInput))
                .collect(toSet());
    }

    private Stream<Point> getNeighbourPlaces(Point point, char[][] parsedInput) {
        return Stream.of(
                        new Point(point.x - 1, point.y),
                        new Point(point.x + 1, point.y),
                        new Point(point.x, point.y - 1),
                        new Point(point.x, point.y + 1)
                )
                .filter(p -> p.x >= 0 && p.y >= 0 && p.x < parsedInput.length && p.y < parsedInput[p.x].length)
                .filter(p -> parsedInput[p.x][p.y] != '#');
    }

    private Point findStart(char[][] parsedInput) {
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'S') {
                    return new Point(i, j);
                }
            }
        }

        throw new IllegalStateException("No start found");
    }

    @Override
    public Long solve2(char[][] parsedInput) {
        var start = findStart(parsedInput);
        parsedInput[start.x][start.y] = '.';

        int dots = countDots(parsedInput);
        System.out.println("Dots: " + dots);

        var gridSize = parsedInput.length;

//        var steps = ITERATIONS / gridSize;
        var steps = 10 / gridSize;

        var result = 0L;


        return result;
    }

    private int countDots(char[][] parsedInput) {
        int dots = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == '.') {
                    dots++;
                }
            }
        }
        return dots;
    }

    private record Point(int x, int y) {
    }

    private record Step(Point p, int s) {
    }

}
