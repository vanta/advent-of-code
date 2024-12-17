package pl.vanta.adventofcode.year2024;

import java.util.Arrays;

import pl.vanta.adventofcode.ParserSolver;

public class Day17 implements ParserSolver<Day17.Input, String> {

    @Override
    public int getDayNumber() {
        return 16;
    }

    @Override
    public Day17.Input parse(String lines) {


        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public String solve(Day17.Input input) {

        return "";
    }

    @Override
    public String solve2(Day17.Input parsedInput) {
        return "";
    }

    record Input(String a, String b, String c, String program) {
    }
}
