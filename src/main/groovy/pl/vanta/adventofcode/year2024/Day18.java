package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.List;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;

public class Day18 implements ParserSolver<List<Location>, Integer> {

    @Override
    public int getDayNumber() {
        return 18;
    }

    @Override
    public List<Location> parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(s -> s.split(","))
                .map(s -> new Location(parseInt(s[0]), parseInt(s[1])))
                .toList();
    }

    @Override
    public Integer solve(List<Location> input) {
        return 0;
    }

    @Override
    public Integer solve2(List<Location> parsedInput) {
        return 0;
    }

}
