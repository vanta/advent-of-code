package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

public class Day3 extends BaseDay<List<String>, Integer> {
    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {


        return 0;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return 0;
    }
}
