package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.union;
import static java.util.Arrays.stream;
import static pl.vanta.adventofcode.Utils.indexesOf;

public class Day7 extends BaseDay<List<String>, Long> {
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
    public Long solve(List<String> parsedInput) {
        var splits = 0L;

        var beams = new boolean[parsedInput.getFirst().length()];
        beams[parsedInput.getFirst().indexOf('S')] = true;

        for (String line : parsedInput.subList(1, parsedInput.size())) {
            for (int i = 0; i < line.length(); i++) {
                if (beams[i] && line.charAt(i) == '^') {
                    splits++;
                    beams[i] = false;
                    beams[i - 1] = true;
                    beams[i + 1] = true;
                }
            }
        }

        return splits;
    }

    @Override
    public Long solve2(List<String> parsedInput) {
        var beams = new long[parsedInput.getFirst().length()];
        beams[parsedInput.getFirst().indexOf('S')] = 1L;

        for (String line : parsedInput.subList(1, parsedInput.size())) {
            for (int i = 0; i < line.length(); i++) {
                if (beams[i] > 0 && line.charAt(i) == '^') {
                    beams[i - 1] += beams[i];
                    beams[i + 1] += beams[i];
                    beams[i] = 0;
                }
            }
        }

        return stream(beams).sum();
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
