package pl.vanta.adventofcode.year2021

import static java.lang.Integer.signum
import static java.lang.Math.abs
import static java.lang.Math.max

class Day5 {
    static List<Line> parse(String input) {
        input.split("\n")
                .collect {
                    def numbers = it.split('->')
                    def n1 = numbers[0].trim().split(',')
                    def n2 = numbers[1].trim().split(',')

                    new Line(
                            p1: new Point(x: n1[0] as int, y: n1[1] as int),
                            p2: new Point(x: n2[0] as int, y: n2[1] as int)
                    )
                }
    }

    static int solve(List<Line> lines) {
        int size = 1000
        int[][] marked = new int[size][size]

        lines
                .findAll { it.isHorizontalOrVertica() }
                .each {
                    markLine(it, marked)
                }

        countIntersections(size, marked)
    }

    static int solve2(List<Line> lines) {
        int size = 1000
        int[][] marked = new int[size][size]

        lines
                .findAll { it.isHorizontalOrVertica() || it.isDiagonal() }
                .each {
                    markLine(it, marked)
                }

        countIntersections(size, marked)
    }

    static void markLine(Line line, int[][] marked) {
        def points = line.getPoints()

        points.each {
            marked[it.x][it.y]++
        }
    }

    private static int countIntersections(int size, int[][] marked) {
        int result = 0
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (marked[i][j] > 1) {
                    result++
                }
            }
        }
        result
    }

    static class Line {
        Point p1, p2

        boolean isHorizontalOrVertica() {
            (p1.y == p2.y) || (p1.x == p2.x)
        }

        boolean isDiagonal() {
            (p1.x - p1.y == p2.x - p2.y) || (p1.x + p1.y == p2.x + p2.y)
        }

        int length() {
            max(abs(p1.x - p2.x), abs(p1.y - p2.y))
        }

        List<Point> getPoints() {
            def points = new ArrayList<Point>()

            for (int i = 0; i <= length(); i++) {
                points << new Point(
                        x: p1.x + (i * signum(p2.x - p1.x)),
                        y: p1.y + (i * signum(p2.y - p1.y))
                )
            }

            points
        }
    }

    static class Point {
        int x, y
    }
}
