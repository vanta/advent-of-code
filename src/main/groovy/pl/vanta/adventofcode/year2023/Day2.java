package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day2 implements ParserSolver<List<Day2.Game>, Integer> {

    private static final Map<String, Integer> LIMITS = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    @Override
    public int getDayNumber() {
        return 2;
    }

    @Override
    public List<Game> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(this::toGame)
                .toList();
    }

    private Game toGame(String line) {
        var s1 = line.split(":");

        var bags = new ArrayList<Map<String, Integer>>();
        for (String string : s1[1].trim().split(";")) {
            var bag = string.trim();

            var cubes = new HashMap<String, Integer>();
            for (String s : bag.split(",")) {
                var c = s.trim().split(" ");

                cubes.put(c[1].trim(), parseInt(c[0].trim()));
            }

            bags.add(cubes);
        }

        return new Game(parseInt(s1[0].replace("Game ", ""), 10), bags);
    }

    @Override
    public Integer solve(List<Game> parsedInput) {
        return parsedInput.stream()
                .filter(this::checkGame)
                .map(Game::id)
                .reduce(0, Integer::sum);
    }

    private boolean checkGame(Game game) {
        return game.bags.stream()
                .allMatch(this::checkBags);
    }

    private boolean checkBags(Map<String, Integer> stringIntegerMap) {
        return stringIntegerMap.entrySet().stream()
                .allMatch(entry -> LIMITS.get(entry.getKey()) >= entry.getValue());
    }

    @Override
    public Integer solve2(List<Game> parsedInput) {
        return parsedInput.stream()
                .map(this::getPower)
                .reduce(0, Integer::sum);
    }

    private int getPower(Game game) {
        return getMax("red", game.bags) * getMax("green", game.bags) * getMax("blue", game.bags);
    }

    private int getMax(String color, List<Map<String, Integer>> bags) {
        return bags.stream()
                .map(bag -> bag.getOrDefault(color, 0))
                .max(Integer::compareTo)
                .orElse(0);
    }

    public record Game(int id, List<Map<String, Integer>> bags) {
    }
}
