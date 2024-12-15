package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

public class Day15 implements ParserSolver<Day15.Input, Integer> {

    @Override
    public int getDayNumber() {
        return 15;
    }

    @Override
    public Day15.Input parse(String lines) {
        var tokenizer = new StringTokenizer(lines, "\n");

        var map = new ArrayList<char[]>();
        var moves = new StringBuilder();

        while (tokenizer.hasMoreTokens()) {
            var line = tokenizer.nextToken();

            if (line.startsWith("#")) {
                map.add(line.toCharArray());
            } else if (!line.isBlank()) {
                moves.append(line);
            }
        }

        return new Day15.Input(map.toArray(char[][]::new), moves.toString());
    }

    @Override
    public Integer solve(Day15.Input parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(Day15.Input parsedInput) {
        return -1;
    }

    private Location findRobot(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '@') {
                    return new Location(j, i);
                }
            }
        }

        return null;
    }

    public record Input(char[][] map, String moves) {

    }

    record Robot(Location location, char direction) {
//        Robot move(int sizeX, int sizeY) {
//            var newLocation = location.move(vx, vy).teleport(sizeX, sizeY);
//
//            return new Day14.Robot(newLocation, vx, vy);
//        }
    }
}
