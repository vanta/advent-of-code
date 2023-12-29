package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Collection;
import java.util.HashSet;
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
        parsedInput[start.x][start.y] = '.';

        Collection<Point> places = new HashSet<>();
        places.add(start);

        var iterations = 1_000;
        for (int i = 0; i < iterations; i++) {
            places = getNextStepPlaces(places, parsedInput);
        }

        return (long) places.size();
    }

    private Collection<Point> getNextStepPlaces(Collection<Point> places, char[][] parsedInput) {
        return places.parallelStream()
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
                .filter(p -> parsedInput[p.x][p.y] == '.');
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

        return 0L;
    }

    private record Point(int x, int y) {
    }
}
