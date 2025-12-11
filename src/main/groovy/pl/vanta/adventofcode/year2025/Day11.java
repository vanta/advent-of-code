package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.IntStream.range;

public class Day11 extends BaseDay<List<Day11.Machine>, Integer> {
    @Override
    public int getDayNumber() {
        return 11;
    }

    @Override
    public List<Machine> parse(String input) {
        return stream(input.split("\n"))
                .map(Machine::parse)
                .toList();
    }

    @Override
    public Integer solve(List<Machine> parsedInput) {
        return 111;
    }


    @Override
    public Integer solve2(List<Machine> parsedInput) {


        return 11;
    }

}
