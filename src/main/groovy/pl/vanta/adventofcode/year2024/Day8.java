package pl.vanta.adventofcode.year2024;

import java.util.HashSet;
import java.util.stream.Stream;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
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
        var frequenciesMap = new ArrayListValuedHashMap<Character, Location>();

        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] != '.') {
                    frequenciesMap.put(parsedInput[i][j], new Location(i, j));
                }
            }
        }

        var antiNodeLocations = new HashSet<Location>();

        for (var freq : frequenciesMap.keySet()) {
            var locations = generatePairs(frequenciesMap.get(freq)).stream()
                    .map(Day8::extracted)
                    .flatMap(p -> Stream.of(p.getLeft(), p.getRight()))
                    .collect(toSet());

            antiNodeLocations.addAll(locations);
        }

        return (int) antiNodeLocations.stream()
                .filter(l -> inBounds(l, parsedInput.length, parsedInput[0].length))
                .count();
    }

    private static Pair<Location, Location> extracted(Pair<Location, Location> pair) {
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
