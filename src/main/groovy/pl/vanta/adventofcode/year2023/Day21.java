package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Day21 implements ParserSolver<char[][], Long> {

    private static final int STEPS = 26_501_365;

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
        return find(parsedInput, findStart(parsedInput), 6);
    }

    @Override
    public Long solveReal(char[][] parsedInput) {
        return find(parsedInput, findStart(parsedInput), 64);
    }

    private long find(char[][] parsedInput, Point start, int iterations) {
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

        return result.size();
    }

//    private Collection<Point> getNextStepPlaces(Collection<Point> places, char[][] parsedInput) {
//        return places.stream()
//                .flatMap(point -> Stream.of(
//                        new Point(point.x - 1, point.y),
//                        new Point(point.x + 1, point.y),
//                        new Point(point.x, point.y - 1),
//                        new Point(point.x, point.y + 1)
//                ))
//                .filter(p -> p.x >= 0 && p.y >= 0 && p.x < parsedInput.length && p.y < parsedInput[p.x].length)
//                .filter(p -> parsedInput[p.x][p.y] != '#')
//                .collect(toSet());
//    }

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

    @Override
    public Long solve2Real(char[][] parsedInput) {
        assert parsedInput.length == parsedInput[0].length;
        int size = parsedInput.length;

        var start = findStart(parsedInput);
        assert start.x == start.y;
        assert start.x == size / 2;

        assert STEPS % size == size / 2;

        int gridWidth = STEPS / size - 1;
        var odd = BigInteger.valueOf(gridWidth / 2 * 2 + 1).pow(2).longValue();
        var even = BigInteger.valueOf((gridWidth + 1) / 2 * 2).pow(2).longValue();

        long oddPoints = find(parsedInput, start, size * 2 + 1);
        long evenPoints = find(parsedInput, start, size * 2);

        long cornerTop = find(parsedInput, new Point(size - 1, start.y), size - 1);
        long cornerBottom = find(parsedInput, new Point(0, start.y), size - 1);
        long cornerRight = find(parsedInput, new Point(start.x, 0), size - 1);
        long cornerLeft = find(parsedInput, new Point(start.x, size - 1), size - 1);

        long smallTopRight = find(parsedInput, new Point(size - 1, 0), size / 2 - 1);
        long smallTopLeft = find(parsedInput, new Point(size - 1, size - 1), size / 2 - 1);
        long smallBottomRight = find(parsedInput, new Point(0, 0), size / 2 - 1);
        long smallBottomLeft = find(parsedInput, new Point(0, size - 1), size / 2 - 1);

        long largeTopRight = find(parsedInput, new Point(size - 1, 0), size * 3 / 2 - 1);
        long largeTopLeft = find(parsedInput, new Point(size - 1, size - 1), size * 3 / 2 - 1);
        long largeBottomRight = find(parsedInput, new Point(0, 0), size * 3 / 2 - 1);
        long largeBottomLeft = find(parsedInput, new Point(0, size - 1), size * 3 / 2 - 1);

        long result =
                odd * oddPoints +
                        even * evenPoints +
                        cornerTop + cornerBottom + cornerRight + cornerLeft +
                        (gridWidth + 1) * (smallTopRight + smallTopLeft + smallBottomRight + smallBottomLeft) +
                        gridWidth * (largeTopRight + largeTopLeft + largeBottomRight + largeBottomLeft);


        System.out.println(result);

        return result;
    }

    private record Point(int x, int y) {
    }

    private record Step(Point p, int s) {
    }

}
