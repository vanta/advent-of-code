package pl.vanta.adventofcode.year2025;

import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static pl.vanta.adventofcode.Gatherers.cartesian;

public class Day4 extends BaseDay<char[][], Integer> {
    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return getToBeRemoved(parsedInput).size();
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        var end = false;
        var removed = 0;

        do {
            var toBeRemoved = getToBeRemoved(parsedInput);
            if (toBeRemoved.isEmpty()) {
                end = true;
            }

            toBeRemoved.forEach(pair ->
                    parsedInput[pair.getLeft()][pair.getRight()] = '.'
            );

            removed += toBeRemoved.size();
        } while (!end);

        return removed;
    }

    private Set<Pair<Integer, Integer>> getToBeRemoved(char[][] parsedInput) {
        var si = range(0, parsedInput.length).boxed();
        var sj = range(0, parsedInput.length).boxed();

        return si.gather(cartesian(sj, Pair::of))
                .filter(pair -> parsedInput[pair.getLeft()][pair.getRight()] == '@')
                .filter(pair -> countNeighbors(parsedInput, pair.getLeft(), pair.getRight()) < 4)
                .collect(toSet());
    }

    private int countNeighbors(char[][] parsedInput, int i, int j) {
        int result = 0;

        if (i > 0 && parsedInput[i - 1][j] == '@') result++; // up
        if (i < parsedInput.length - 1 && parsedInput[i + 1][j] == '@') result++; // down
        if (j > 0 && parsedInput[i][j - 1] == '@') result++; // left
        if (j < parsedInput[i].length - 1 && parsedInput[i][j + 1] == '@') result++; // right
        if (i > 0 && j > 0 && parsedInput[i - 1][j - 1] == '@') result++; // up-left
        if (i > 0 && j < parsedInput[i].length - 1 && parsedInput[i - 1][j + 1] == '@') result++; // up-right
        if (i < parsedInput.length - 1 && j > 0 && parsedInput[i + 1][j - 1] == '@') result++; // down-left
        if (i < parsedInput.length - 1 && j < parsedInput[i].length - 1 && parsedInput[i + 1][j + 1] == '@') result++; // down-right

        return result;
    }

}
