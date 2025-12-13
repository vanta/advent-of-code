package pl.vanta.adventofcode.year2025;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class Day12 extends BaseDay<List<Day12.Input>, Long> {


    @Override
    public int getDayNumber() {
        return 12;
    }

    @Override
    public List<Day12.Input> parse(String input) {
        return stream(asList(input.split("\n\n")).getLast().split("\n"))
                .map(Day12::parseLine)
                .toList();
    }

    private static Input parseLine(String line) {
        var lineParts = line.split(":");
        var dim = lineParts[0].split("x");
        var shapes = stream(lineParts[1].trim().split(" ")).map(Integer::parseInt).toList();

        return new Input(parseInt(dim[0]), parseInt(dim[1]), shapes);
    }

    @Override
    public Long solve(List<Day12.Input> parsedInput) {
        return parsedInput.stream()
                .filter(i -> i.h * i.w >= i.shapes.stream().map(n -> n * 9).reduce(0, Integer::sum))
                .count();
    }

    @Override
    public Long solve2(List<Day12.Input> parsedInput) {
        return 333L;
    }


    public record Input(int w, int h, List<Integer> shapes) {
    }
}
