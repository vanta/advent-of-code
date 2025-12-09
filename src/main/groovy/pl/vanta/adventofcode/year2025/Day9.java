package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class Day9 extends BaseDay<List<Day9.Box>, Long> {
    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public List<Day9.Box> parse(String input) {
        return stream(input.split("\n"))
                .map(l -> l.split(","))
                .map(a -> new Day9.Box(parseInt(a[0]), parseInt(a[1]), parseInt(a[2])))
                .toList();
    }

    @Override
    public Long solve(List<Day9.Box> parsedInput) {
        var limit = 1000; //1000 for real data
        var connections = getConnections(parsedInput, limit);

        var circuits = flatten(parsedInput);

        for (var conn : connections) {
            List<Set<Box>> combined = new ArrayList<>();

            for (var circuit : circuits) {
                if (circuit.contains(conn.b1) || circuit.contains(conn.b2)) {
                    circuit.add(conn.b1);
                    circuit.add(conn.b2);
                    combined.add(circuit);
                }
            }

            if (combined.size() > 1) {
                var total = new HashSet<Box>();
                for (var circuit : combined) {
                    circuits.remove(circuit);
                    total.addAll(circuit);
                }
                circuits.add(total);
            }
        }

        return (long) circuits.stream()
                .map(Set::size)
                .sorted(reverseOrder())
                .limit(3)
                .reduce(1, (a, b) -> a * b);
    }

    private static List<Set<Box>> flatten(List<Box> parsedInput) {
        return parsedInput.stream()
                .map(b -> new HashSet<>(Set.of(b)))
                .map(s -> (Set<Box>) s)
                .collect(toList());
    }

    private static List<Connection> getConnections(List<Box> parsedInput, int limit) {
        var allConnections = new ArrayList<Connection>();

        //generating all connections
        for (int i = 0; i < parsedInput.size(); i++) {
            var j1 = parsedInput.get(i);
            for (int j = i + 1; j < parsedInput.size(); j++) {
                var j2 = parsedInput.get(j);
                allConnections.add(new Connection(j1, j2, distance(j1, j2)));
            }
        }

        //cut to limit of closest connections
        return allConnections.stream()
                .sorted(comparing(Connection::dist))
                .limit(limit)
                .toList();
    }

    @Override
    public Long solve2(List<Day9.Box> parsedInput) {
        var limit = 10_000;
        var connections = getConnections(parsedInput, limit);

        var circuits = flatten(parsedInput);

        for (var conn : connections) {
            List<Set<Box>> combined = new ArrayList<>();

            for (var circuit : circuits) {
                if (circuit.contains(conn.b1) || circuit.contains(conn.b2)) {
                    circuit.add(conn.b1);
                    circuit.add(conn.b2);
                    combined.add(circuit);
                }
            }

            if (combined.size() > 1) {
                var total = new HashSet<Box>();
                for (var circuit : combined) {
                    circuits.remove(circuit);
                    total.addAll(circuit);
                }
                circuits.add(total);
            }

            if (circuits.size() == 1 && circuits.getFirst().size() == parsedInput.size()) {
                return conn.b1.x * conn.b2.x;
            }
        }

        throw new IllegalArgumentException("No pair of junction boxes leads to 1 big circuit");
    }

    private static long distance(Day9.Box p1, Day9.Box p2) {
        var dx = p1.x - p2.x;
        var dy = p1.y - p2.y;
        var dz = p1.z - p2.z;

        return dx * dx + dy * dy + dz * dz;
    }

    public record Box(long x, long y, long z) {
    }

    public record Connection(Box b1, Box b2, long dist) {
    }
}
