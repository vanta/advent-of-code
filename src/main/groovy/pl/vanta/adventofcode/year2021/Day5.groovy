package pl.vanta.adventofcode.year2021

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

        markLines(lines, marked)
        countIntersections(size, marked)
    }

    private static List<Line> markLines(List<Line> lines, int[][] marked) {
        lines
                .findAll { it.isHorizontal() || it.isVertical() }
                .each {
                    markLine(it, marked)
                }
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

    static int solve2(List<Line> lines) {

        -1
    }

    static class Line {
        Point p1, p2

        boolean isHorizontal() {
            p1.y == p2.y
        }

        boolean isVertical() {
            p1.x == p2.x
        }

        List<Point> getPoints() {
            def points = new ArrayList<Point>()

            if (isHorizontal()) {
                if (p1.x > p2.x) {
                    for (int i = p2.x; i <= p1.x; i++) {
                        points << new Point(x: i, y: p1.y)
                    }
                } else {
                    for (int i = p1.x; i <= p2.x; i++) {
                        points << new Point(x: i, y: p1.y)
                    }
                }
            } else {
                if (p1.y > p2.y) {
                    for (int i = p2.y; i <= p1.y; i++) {
                        points << new Point(x: p1.x, y: i)
                    }
                } else {
                    for (int i = p1.y; i <= p2.y; i++) {
                        points << new Point(x: p1.x, y: i)
                    }
                }
            }

            points
        }

        @Override
        String toString() {
            "$p1 -> $p2 (${isHorizontal() || isVertical()})"
        }
    }

    static class Point {
        int x, y

        @Override
        String toString() {
            "$x,$y"
        }
    }
}
