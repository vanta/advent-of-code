package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Day6 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public List<String> parse(String lines) {
        var split = lines.split("\n");

        var times = split[0].substring(10).trim();
        var dists = split[1].substring(10).trim();

        return List.of(times, dists);
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        var times = Stream.of(parsedInput.get(0).split("\\s+"))
                .toList();

        var dists = Stream.of(parsedInput.get(1).split("\\s+"))
                .toList();

        var wins = new ArrayList<Integer>();

        for (int j = 0; j < times.size(); j++) {
            wins.add(countWins(parseInt(times.get(j)), parseInt(dists.get(j))));
        }

        return wins.stream().reduce(1, (a, b) -> a * b);
    }

    private static int countWins(long time, long dist) {
        var winning = 0;
        for (int speed = 0; speed <= time; speed++) {
            var tmpDist = (time - speed) * speed;

            if (tmpDist > dist) {
                winning++;
            }
        }
        return winning;
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        var time = parseLong(parsedInput.get(0).replaceAll("\\s+", ""));
        var dist = parseLong(parsedInput.get(1).replaceAll("\\s+", ""));

        return countWins(time, dist);
    }
}
