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
    public List<Day18.Dig> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(this::parseLine)
                .toList();
    }

    private Dig parseLine(String s) {
        Scanner scanner = new Scanner(s);
        return new Dig(scanner.next().charAt(0), scanner.nextInt());
    }

    @Override
    public Long solve(List<Day18.Dig> parsedInput) {
        var points = getPoints(parsedInput);

        var b = getLength(points);
        var A = insideArea(points);

        return A + b/2 + 1L;
    }

    private int insideArea(List<Point> points) {
        int area = 0;

        for (int i = 1; i < points.size(); i++) {
            area += (points.get(i - 1).x * points.get(i).y - points.get(i).x * points.get(i - 1).y);
        }

        return Math.abs(area / 2);
    }

    private int getLength(List<Point> points) {
        int len = 0;
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

    @Override
    public Long solve2(List<Day18.Dig> parsedInput) {
        return 0L;
    }

    public record Dig(char dir, int meters) {
    }

    record Point(int x, int y) {
    }
}
