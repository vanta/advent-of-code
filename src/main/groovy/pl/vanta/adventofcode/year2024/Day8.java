package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;
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
        return solveInternal(parsedInput, pair -> generateAntiNodes(pair, parsedInput.length, parsedInput[0].length));
    }

    private static int solveInternal(char[][] parsedInput,
                                     Function<Pair<Location, Location>, Set<Location>> generateAntiNodes) {
        return (int) generateFrequenciesMap(parsedInput).values().stream()
                .flatMap(locations -> generateAntiNodesLocations(locations, generateAntiNodes))
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

    private static Stream<Location> generateAntiNodesLocations(List<Location> locations,
                                                               Function<Pair<Location, Location>, Set<Location>> generateAntiNodes) {
        return generatePairs(locations).stream()
                .map(generateAntiNodes)
                .flatMap(Collection::stream);
    }

    private static Set<Location> generateAntiNodes(Pair<Location, Location> pair, int sizeX, int sizeY) {
        var l1 = pair.getLeft();
        var l2 = pair.getRight();

        var dx = l1.x() - l2.x();
        var dy = l1.y() - l2.y();

        var n1 = new Location(l1.x() + dx, l1.y() + dy);
        var n2 = new Location(l2.x() - dx, l2.y() - dy);

        return Set.of(n1, n2).stream()
                .filter(l -> inBounds(l, sizeX, sizeY))
                .collect(toSet());
    }

    private static Set<Location> generateAntiNodesAll(Pair<Location, Location> pair, int sizeX, int sizeY) {
        var l1 = pair.getLeft();
        var l2 = pair.getRight();

        var dx = l1.x() - l2.x();
        var dy = l1.y() - l2.y();

        var n1 = new Location(l1.x() + dx, l1.y() + dy);
        var n2 = new Location(l2.x() - dx, l2.y() - dy);

        return Set.of(n1, n2, l1, l2);
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return solveInternal(parsedInput, pair -> generateAntiNodesAll(pair, parsedInput.length, parsedInput[0].length));
    }

}
