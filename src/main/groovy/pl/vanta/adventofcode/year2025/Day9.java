package pl.vanta.adventofcode.year2025;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.stream;

public class Day9 extends BaseDay<List<Pair<Integer, Integer>>, Long> {
    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public List<Pair<Integer, Integer>> parse(String input) {
        return stream(input.split("\n"))
                .map(l -> l.split(","))
                .map(a -> Pair.of(parseInt(a[0]), parseInt(a[1])))
                .toList();
    }

    @Override
    public Long solve(List<Pair<Integer, Integer>> parsedInput) {
        long max = -1;

        for (int i = 0; i < parsedInput.size(); i++) {
            var j1 = parsedInput.get(i);
            for (int j = i + 1; j < parsedInput.size(); j++) {
                var j2 = parsedInput.get(j);

                long area = (long) abs(1 + j1.getLeft() - j2.getLeft()) * abs(1 + j1.getRight() - j2.getRight());
                if (area > max) {
                    max = area;
                }
            }
        }

        return max;
    }

    @Override
    public Long solve2(List<Pair<Integer, Integer>> parsedInput) {
        long max = -1;
        var cache = new HashSet<Pair<Integer, Integer>>();

        for (int i = 0; i < parsedInput.size(); i++) {
            var j1 = parsedInput.get(i);
            for (int j = i + 1; j < parsedInput.size(); j++) {
                var j2 = parsedInput.get(j);

                long area = (long) abs(1 + j1.getLeft() - j2.getLeft()) * abs(1 + j1.getRight() - j2.getRight());
                if (area > max) {
                    var all = allRectanglePerimeterPoints(
                            Pair.of(j1.getLeft(), j1.getRight()),
                            Pair.of(j2.getLeft(), j2.getRight()));

                    if (all.stream().allMatch(p -> inside(parsedInput, cache, p.getLeft(), p.getRight()))) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }

    private static boolean inside(List<Pair<Integer, Integer>> poly, Set<Pair<Integer, Integer>> cache, int px, int py) {
        if (cache.contains(Pair.of(px, py))) {
            return true;
        }

        if (isOnBoundary(Pair.of(px, py), poly)) {
            cache.add(Pair.of(px, py));
            return true;
        }

        boolean inside = false;
        int n = poly.size();

        for (int i = 0; i < n; i++) {
            var a = poly.get(i);
            var b = poly.get((i + 1) % n);

            // Only consider vertical edges
            if (a.getLeft().equals(b.getLeft())) {
                int x = a.getLeft();

                int yMin = min(a.getRight(), b.getRight());
                int yMax = max(a.getRight(), b.getRight());

                // Does the ray intersect this vertical edge?
                if (py >= yMin && py < yMax && px < x) {
                    inside = !inside;
                }
            }
        }
        if (inside) {
            cache.add(Pair.of(px, py));
        }

        return inside;
    }

    private Set<Pair<Integer, Integer>> allRectanglePerimeterPoints(Pair<Integer, Integer> v1, Pair<Integer, Integer> v2) {
        var minX = min(v1.getLeft(), v2.getLeft());
        var maxX = max(v1.getLeft(), v2.getLeft());
        var minY = min(v1.getRight(), v2.getRight());
        var maxY = max(v1.getRight(), v2.getRight());

        var points = new HashSet<Pair<Integer, Integer>>();
        for (int x = minX; x <= maxX; x++) {
            points.add(Pair.of(x, minY));
            points.add(Pair.of(x, maxY));
        }
        for (int y = minY; y <= maxY; y++) {
            points.add(Pair.of(minX, y));
            points.add(Pair.of(maxX, y));
        }
        return points;
    }

    private static boolean isOnBoundary(Pair<Integer, Integer> p, List<Pair<Integer, Integer>> poly) {
        int n = poly.size();

        for (int i = 0; i < n; i++) {
            var a = poly.get(i);
            var b = poly.get((i + 1) % n);

            // Horizontal edge
            if (a.getRight().equals(b.getRight())) {
                if (p.getRight().equals(a.getRight()) &&
                        p.getLeft() >= min(a.getLeft(), b.getLeft()) &&
                        p.getLeft() <= max(a.getLeft(), b.getLeft())) {
                    return true;
                }
            }

            // Vertical edge
            if (a.getLeft().equals(b.getLeft())) {
                if (p.getLeft().equals(a.getLeft()) &&
                        p.getRight() >= min(a.getRight(), b.getRight()) &&
                        p.getRight() <= max(a.getRight(), b.getRight())) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
