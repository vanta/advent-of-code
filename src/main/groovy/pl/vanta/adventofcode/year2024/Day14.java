package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;
import pl.vanta.adventofcode.Utils;

import static java.lang.Integer.parseInt;

public class Day14 implements ParserSolver<List<Day14.Robot>, Integer> {

    @Override
    public int getDayNumber() {
        return 14;
    }

    @Override
    public List<Robot> parse(String lines) {
    }

    @Override
    public Integer solve(List<Robot> parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(List<Robot> parsedInput) {
        return-1;
    }

    record Robot(Location location, int vx, int vy) {}
}
