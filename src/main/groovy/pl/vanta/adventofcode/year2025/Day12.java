package pl.vanta.adventofcode.year2025;

import java.util.List;

import static java.util.Arrays.stream;

public class Day12 extends BaseDay<List<Day12.Input>, Long> {


    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public List<Day12.Input> parse(String input) {
        return stream(input.split("\n"))
                .map(Input::parse)
                .toList();
    }

    @Override
    public Long solve(List<Day12.Input> parsedInput) {
        
        return 222L;
    }

    @Override
    public Long solve2(List<Day12.Input> parsedInput) {
        return 333L;
    }


    public record Input() {
    }
}
