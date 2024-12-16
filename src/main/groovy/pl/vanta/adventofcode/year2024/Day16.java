package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Optional.empty;

public class Day16 implements ParserSolver<Day16.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 16;
    }

    @Override
    public Day16.Input parse(String lines) {

    }

    @Override
    public Integer solve(Day16.Input input) {

    }

    @Override
    public Integer solve2(Day16.Input parsedInput) {
        return -1;
    }

    public record Input(char[][] map, String moves) {

    }
}
