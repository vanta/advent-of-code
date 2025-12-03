package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day1 extends BaseDay<List<Integer>, Integer, Integer> {
    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<Integer> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(s -> (s.charAt(0) == 'L' ? -1 : 1) * parseInt(s.substring(1)))
                .toList();
    }

    @Override
    public Integer solve(List<Integer> parsedInput) {
        return (int) parsedInput.stream()
                .gather(Gatherers.scan(() -> 50, Day1::rotate))
                .filter(i -> i == 0)
                .count();
    }

    private static int rotate(Integer a, Integer b) {
        var sum = a + b;

        if (sum >= 100) {
            return sum - 100;
        } else if (sum < 0) {
            return sum + 100;
        } else {
            return sum;
        }
    }

    @Override
    public Integer solve2(List<Integer> parsedInput) {
        return 0;
    }

}
