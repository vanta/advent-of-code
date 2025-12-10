package pl.vanta.adventofcode.year2025;

import java.util.List;

import static java.util.Arrays.stream;

public class Day10 extends BaseDay<List<Day10.Machine>, Integer> {
    @Override
    public int getDayNumber() {
        return 10;
    }

    @Override
    public List<Machine> parse(String input) {
        return stream(input.split("\n"))
                .map(Machine::parse)
                .toList();
    }

    @Override
    public Integer solve(List<Machine> parsedInput) {

        return 21;
    }

    @Override
    public Integer solve2(List<Machine> parsedInput) {

        return 11;
    }

    public record Machine() {
        public static Machine parse(String s) {
            return null;
        }
    }
}
