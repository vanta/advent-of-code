package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.util.Optional.empty;

public class Day15 implements ParserSolver<Day15.Input, Integer> {
    private static final char ROBOT = '@';
    private static final char BOX = 'O';
    private static final char WALL = '#';
    private static final char EMPTY = '.';

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
            print(input.map, position);
            System.out.println("Move: " + move);
            var nexPos = position.move(move);

            //wall
            var whereIsFreeSpace = whereIsFreeSpace(input.map, nexPos, move);
            if (whereIsFreeSpace.isEmpty()) {
                continue;
            }

            //empty
            var freeSpacePosition = whereIsFreeSpace.get();
            position = nexPos;

            if (freeSpacePosition.equals(nexPos)) {
                continue;
            }

            //pushing
            input.map[freeSpacePosition.x()][freeSpacePosition.y()] = BOX;
            input.map[position.x()][position.y()] = EMPTY;
        }

        return findGoods(input.map).stream()
                .mapToInt(l -> 100 * l.x() + l.y())
                .sum();
    }

    private Optional<Location> whereIsFreeSpace(char[][] map, Location nexPos, char direction) {
        var newLocation = nexPos;

        while (map[newLocation.x()][newLocation.y()] != WALL) {
            if (map[newLocation.x()][newLocation.y()] == EMPTY) {
                return Optional.of(newLocation);
            }

            newLocation = newLocation.move(direction);
        }

        return empty();
    }

    private List<Location> findGoods(char[][] map) {
        var result = new ArrayList<Location>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == BOX) {
                    result.add(new Location(i, j));
                }
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
                if (map[i][j] == ROBOT) {
                    map[i][j] = EMPTY;
                    return new Location(j, i);
                }
            }
        }

        throw new IllegalStateException("Robot not found");
    }

    private void print(char[][] map, Location robot) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (robot.x() == i && robot.y() == j) {
                    System.out.print("@");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
    }

    public record Input(char[][] map, String moves) {

    }
}
