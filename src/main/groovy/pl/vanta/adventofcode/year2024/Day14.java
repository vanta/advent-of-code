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
//        int sizeX = 101;
//        int sizeY = 103;
        int sizeX = 11;
        int sizeY = 7;

        int seconds = 100;

        var temp = parsedInput;
        for (int i = 0; i < seconds; i++) {
            temp = temp.stream()
                    .map(robot -> robot.move(sizeX, sizeY))
                    .toList();
        }

        var sizeX2 = sizeX / 2;
        var sizeY2 = sizeY / 2;
        int q1 = countRobots(temp, 0, 0, sizeX2, sizeY2);
        int q2 = countRobots(temp, 0, sizeY2, sizeX2 + 1, sizeY);
        int q3 = countRobots(temp, sizeX2, 0, sizeX, sizeY2);
        int q4 = countRobots(temp, sizeX2 + 1, sizeY2 + 1, sizeX, sizeY);

        return q1 * q2 * q3 * q4;
    }

    private int countRobots(List<Robot> robots, int x, int y, int maxX, int maxY) {
        return (int) robots.stream()
                .filter(r -> r.location().x() >= x && r.location().x() < maxX && r.location().y() >= y && r.location().y() < maxY)
                .count();
    }

    @Override
    public Integer solve2(List<Robot> parsedInput) {
        return -1;
    }

    public record Robot(Location location, int vx, int vy) {
        Robot move(int sizeX, int sizeY) {
            var newLocation = location.move(vx, vy).teleport(sizeX, sizeY);

            return new Robot(newLocation, vx, vy);
        }
    }
}
