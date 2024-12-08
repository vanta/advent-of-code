package pl.vanta.adventofcode.year2024;

import java.util.HashSet;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
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
            var locationsPairs = generatePairs(frequenciesMap.get(freq));

            for (var pair : locationsPairs) {
                var l1 = pair.getLeft();
                var l2 = pair.getRight();

                var dx = l1.x() - l2.x();
                var dy = l1.y() - l2.y();

                var antiNode1 = new Location(l1.x() + dx, l1.y() + dy);
                var antiNode2 = new Location(l2.x() - dx, l2.y() - dy);

                if (inBounds(antiNode1, parsedInput.length, parsedInput[0].length)) {
                    antiNodeLocations.add(antiNode1);
                }
                if (inBounds(antiNode2, parsedInput.length, parsedInput[0].length)) {
                    antiNodeLocations.add(antiNode2);
                }
            }
        }

//        print(antiNodeLocations, '#', parsedInput);

        return antiNodeLocations.size();
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;

    }

}
