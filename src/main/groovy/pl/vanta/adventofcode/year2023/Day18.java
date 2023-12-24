package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day18 implements ParserSolver<List<Day18.Dig>, Long> {

    @Override
    public int getDayNumber() {
        return 18;
    }

    @Override
    public List<Dig> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(this::parseLine)
                .toList();
    }

    @Override
    public List<Dig> parse2(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(this::parseLine2)
                .toList();
    }

    private Dig parseLine(String s) {
        Scanner scanner = new Scanner(s);
        return new Dig(scanner.next().charAt(0), scanner.nextInt());
    }

    private Dig parseLine2(String s) {
        var i = s.indexOf('#') + 1;
        var n = s.substring(i, i + 5);
        var d = s.charAt(i + 5);

        var dir = switch (d) {
            case '0' -> 'R';
            case '1' -> 'D';
            case '2' -> 'L';
            case '3' -> 'U';
            default -> throw new IllegalStateException("Unexpected value: " + d);
        };

        return new Dig(dir, Integer.parseInt(n, 16));
    }

    @Override
    public Long solve(List<Dig> parsedInput) {
        var points = getPoints(parsedInput);

        var b = getLength(points);
        var A = insideArea(points);

        return A + b/2 + 1L;
    }

    @Override
    public Long solve2(List<Dig> parsedInput) {
        return solve(parsedInput);
    }

    private long insideArea(List<Point> points) {
        long area = 0L;

        for (int i = 1; i < points.size(); i++) {
            var i1 = (long) points.get(i - 1).x * points.get(i).y;
            var i2 = (long) points.get(i).x * points.get(i - 1).y;
            area += (i1 - i2);
        }

        return Math.abs(area / 2);
    }

    private long getLength(List<Point> points) {
        long len = 0;
        for (int i = 1; i < points.size(); i++) {
            len += Math.abs(points.get(i).x - points.get(i - 1).x)
                    + Math.abs(points.get(i).y - points.get(i - 1).y);
        }
        return len;
    }

    private List<Point> getPoints(List<Dig> parsedInput) {
        var points = new LinkedList<Point>();
        points.add(new Point(0, 0));
        for (Dig dig : parsedInput) {
            if (dig.dir == 'U') {
                points.add(new Point(points.getLast().x - dig.meters, points.getLast().y));
            } else if (dig.dir == 'D') {
                points.add(new Point(points.getLast().x + dig.meters, points.getLast().y));
            } else if (dig.dir == 'L') {
                points.add(new Point(points.getLast().x, points.getLast().y - dig.meters));
            } else if (dig.dir == 'R') {
                points.add(new Point(points.getLast().x, points.getLast().y + dig.meters));
            }
        }

        return points;
    }

    public record Dig(char dir, int meters) {
    }

    private record Point(int x, int y) {
    }
}
