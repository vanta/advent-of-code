package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
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
    public Integer solve(Day15.Input input) {
        var position = findRobot(input.map);

        for (var move : input.moves.toCharArray()) {
            var nexPos = position.move(move);

            //wall
            if (input.map[nexPos.y()][nexPos.x()] == '#') {
                continue;
            }

            //empty
            if (input.map[nexPos.y()][nexPos.x()] == '.') {
                position = nexPos;
            }


        }

        return findGoods(input.map).stream()
                .mapToInt(l -> 100 * l.x() + l.y())
                .sum();
    }

    private List<Location> findGoods(char[][] map) {
        var result = new ArrayList<Location>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                result.add(new Location(i, j));
            }
        }

        return result;
    }

    @Override
    public Integer solve2(Day15.Input parsedInput) {
        return -1;
    }

    private Location findRobot(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '@') {
                    map[i][j] = '.';
                    return new Location(j, i);
                }
            }
        }

        throw new IllegalStateException("Robot not found");
    }

    public record Input(char[][] map, String moves) {

    }
}
