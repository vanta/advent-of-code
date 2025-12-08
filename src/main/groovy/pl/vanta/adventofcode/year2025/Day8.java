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
import static java.util.stream.Collectors.toSet;

public class Day8 extends BaseDay<List<Day8.Box>, Long> {
    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public List<Day8.Box> parse(String input) {
        return stream(input.split("\n"))
                .map(l -> l.split(","))
                .map(a -> new Day8.Box(parseInt(a[0]), parseInt(a[1]), parseInt(a[2])))
                .toList();
    }

    @Override
    public Long solve(List<Day8.Box> parsedInput) {
        var limit = 1000; //1000 for real data
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
        var connections = allConnections.stream()
                .sorted(comparing(Connection::dist))
                .limit(limit)
                .toList();

        List<Set<Box>> circuits = parsedInput.stream()
                .map(b -> new HashSet<>(Set.of(b)))
                .map(s -> (Set<Box>) s)
                .collect(toList());

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

    @Override
    public Long solve2(List<Day8.Box> parsedInput) {


        return 0L;
    }

    private static long distance(Day8.Box p1, Day8.Box p2) {
        var dx = p1.x - p2.x;
        var dy = p1.y - p2.y;
        var dz = p1.z - p2.z;

        return dx * dx + dy * dy + dz * dz;
    }

    public record Box(int x, int y, int z) {
    }

    public record Connection(Box b1, Box b2, long dist) {
    }
}
