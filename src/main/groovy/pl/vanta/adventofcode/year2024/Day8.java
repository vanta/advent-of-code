package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static pl.vanta.adventofcode.Utils.generatePairs;
import static pl.vanta.adventofcode.Utils.inBounds;

public class Day8 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public char[][] parse(String lines) {
        return stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        var frequenciesMap = generateFrequenciesMap(parsedInput);

        return (int) frequenciesMap.values().stream()
                .flatMap(Day8::generateLocations)
                .filter(l -> inBounds(l, parsedInput.length, parsedInput[0].length))
                .distinct()
                .count();
    }

    private static Map<Character, List<Location>> generateFrequenciesMap(char[][] parsedInput) {
        var frequenciesMap = new HashMap<Character, List<Location>>();
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] != '.') {
                    if (!frequenciesMap.containsKey(parsedInput[i][j])) {
                        frequenciesMap.put(parsedInput[i][j], new ArrayList<>());
                    }

                    frequenciesMap.get(parsedInput[i][j]).add(new Location(i, j));
                }
            }
        }
        return frequenciesMap;
    }

    private static Stream<Location> generateLocations(List<Location> value) {
        return generatePairs(value).stream()
                .map(Day8::generateAntiNodes)
                .flatMap(p -> Stream.of(p.getLeft(), p.getRight()));
    }

    private static Pair<Location, Location> generateAntiNodes(Pair<Location, Location> pair) {
        var l1 = pair.getLeft();
        var l2 = pair.getRight();

        var dx = l1.x() - l2.x();
        var dy = l1.y() - l2.y();

        var n1 = new Location(l1.x() + dx, l1.y() + dy);
        var n2 = new Location(l2.x() - dx, l2.y() - dy);

        return Pair.of(n1, n2);
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;

    }

}
