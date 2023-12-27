package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day20 implements ParserSolver<List<Day20.Module>, Long> {

    @Override
    public int getDayNumber() {
        return 20;
    }

    @Override
    public List<Day20.Module> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(this::parseModule)
                .toList();
    }

    private Module parseModule(String s) {
        var split = s.split(" -> ");
        var outputs = split[1].split(", ");

        if ("broadcaster".equals(split[0])) {
            return new Module("broadcaster", "broadcaster", Arrays.asList(outputs));
        }

        return new Module("" + split[0].charAt(0), split[0].substring(1), Arrays.asList(outputs));
    }

    @Override
    public Long solve(List<Day20.Module> parsedInput) {
        parsedInput.forEach(System.out::println);


        return 0L;
    }

    @Override
    public Long solve2(List<Day20.Module> parsedInput) {
        return 0L;
    }

    public record Module(String type, String name, List<String> outputs) {
    }
}
