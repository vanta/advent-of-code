package pl.vanta.adventofcode.year2024;

import java.util.Arrays;
import java.util.List;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static pl.vanta.adventofcode.Utils.getTokens;

public class Day14 implements ParserSolver<List<Day14.Robot>, Integer> {

    @Override
    public int getDayNumber() {
        return 14;
    }

    @Override
    public List<Robot> parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(line -> getTokens("p=([0-9-]+),([0-9-]+) v=([0-9-]+),([0-9-]+)", line))
                .map(l -> new Robot(new Location(parseInt(l.get(0)), parseInt(l.get(1))), parseInt(l.get(2)), parseInt(l.get(3))))
                .toList();
    }

    @Override
    public Integer solve(List<Robot> parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(List<Robot> parsedInput) {
        return -1;
    }

    record Robot(Location location, int vx, int vy) {
        public Robot move() {
            return new Robot(new Location(location.x() + vx, location.y() + vy), vx, vy);
        }
    }
}
