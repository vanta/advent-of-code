package pl.vanta.adventofcode.year2025;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
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
        return 0L;
    }

}
