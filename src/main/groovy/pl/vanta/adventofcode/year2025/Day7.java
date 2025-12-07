package pl.vanta.adventofcode.year2025;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.union;
import static java.util.Arrays.stream;
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
        var start = parsedInput.getFirst().indexOf('S');
        return move(parsedInput.subList(1, parsedInput.size()), String.valueOf(start), start).size();
    }

    private static Set<String> move(List<String> list, String path, int index) {
        if (list.isEmpty()) {
            return Set.of(path);
        }

        var sublist = list.subList(1, list.size());

        if (indexesOf(list.getFirst(), '^').contains(index)) {
            return union(
                    move(sublist, path + (index - 1), index - 1),
                    move(sublist, path + (index + 1), index + 1)
            );
        } else {
            return move(sublist, path + index, index);
        }
    }
}
