package pl.vanta.adventofcode.year2025;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class Day9 extends BaseDay<List<Pair<Integer, Integer>>, Integer> {
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
    public Integer solve(List<Pair<Integer, Integer>> parsedInput) {

        return 0;
    }


    @Override
    public Integer solve2(List<Pair<Integer, Integer>> parsedInput) {
        return 0;
    }

}
