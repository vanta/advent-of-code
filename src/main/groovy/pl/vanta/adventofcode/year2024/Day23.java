package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolverGeneric;

import static java.lang.String.join;
import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Day23 implements ParserSolverGeneric<List<String>, Integer, String> {

    @Override
    public int getDayNumber() {
        return 23;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        var map = buildMap(input);

        return (int) findCycles(map).stream()
                .filter(l -> l.stream().anyMatch(s -> s.startsWith("t")))
                .count();
    }

    private static Map<String, Set<String>> buildMap(List<String> input) {
        return input.stream()
                .map(s -> s.split("-"))
                .map(t -> Set.of(Pair.of(t[0], t[1]), Pair.of(t[1], t[0])))
                .flatMap(Set::stream)
                .collect(toMap(
                        Pair::getLeft,
                        p -> Set.of(p.getRight()),
                        Sets::union
                ));
    }

    private static Set<Set<String>> findCycles(Map<String, Set<String>> map) {
        return map.entrySet().stream()
                .map(e -> getCycles(map, e))
                .flatMap(Set::stream)
                .collect(toSet());
    }

    private static Set<Set<String>> getCycles(Map<String, Set<String>> map, Map.Entry<String, Set<String>> e) {
        return e.getValue().stream()
                .map(v -> findCycleInternal(e.getKey(), v, e.getValue(), map))
                .flatMap(Set::stream)
                .filter(not(Set::isEmpty))
                .collect(toSet());
    }

    private static Set<Set<String>> findCycleInternal(String current, String neighbour, Set<String> values, Map<String, Set<String>> map) {
        return map.get(neighbour).stream()
                .filter(values::contains)
                .map(s -> Set.of(current, neighbour, s))
                .collect(toSet());
    }

    @Override
    public String solve2(List<String> input) {
        var map = buildMap(input);

        var cycles3 = findCycles(map);

        var histogram = map.keySet().stream()
                .collect(groupingBy(k -> cycles3.stream().filter(s -> s.contains(k)).count()));

        var max = histogram.keySet().stream().mapToLong(l -> l).max().orElseThrow();

        return join(",", histogram.get(max));
    }

//    private boolean areConnected(Map<String, Set<String>> map, List<String> nodes) {
//        if (nodes.size() == 2) {
//            return map.get(nodes.get(0)).contains(nodes.get(1));
//        }
//
//        var allButFirst = nodes.subList(1, nodes.size());
//
//        if (map.get(nodes.getFirst()).containsAll(allButFirst)) {
//            return areConnected(map, allButFirst);
//        }
//
//        return false;
//    }
}
