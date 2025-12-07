package pl.vanta.adventofcode.year2025;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.vanta.adventofcode.Utils;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static pl.vanta.adventofcode.Utils.indexesOf;

public class Day7 extends BaseDay<List<String>, Integer> {
    @Override
    public int getDayNumber() {
        return 7;
    }

    @Override
    public List<String> parse(String input) {
        return stream(input.split("\n"))
                .filter(line -> line.contains("^") || line.contains("S"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        int splits = 0;
        var beams = new HashSet<Integer>();
        beams.add(parsedInput.getFirst().indexOf('S'));

        for (String line : parsedInput.subList(1, parsedInput.size())) {
            for (int index : indexesOf(line, '^')) {
                if (beams.contains(index)) {
                    splits++;
                    beams.remove(index);
                    beams.add(index - 1);
                    beams.add(index + 1);
                }
            }
        }

        return splits;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        var beams = new HashSet<Integer>();
        var start = parsedInput.getFirst().indexOf('S');

        return move(parsedInput.subList(1, parsedInput.size()), List.of(start)).size();
    }

    private static Set<String> move(List<String> list, List<Integer> path) {
        if (list.isEmpty()) {
            return Set.of(path.stream().map(String::valueOf).collect(joining()));
        }

        var line = list.getFirst();
        var paths = new HashSet<String>();
        var indexes = indexesOf(line, '^');
        var sublist = list.subList(1, list.size());

        var index = path.getLast();

        if (indexes.contains(index)) {
            paths.addAll(move(sublist, Utils.add(path, index - 1)));
            paths.addAll(move(sublist, Utils.add(path, index + 1)));
        } else {
            paths.addAll(move(sublist, Utils.add(path, index)));
        }

        return paths;
    }
}
