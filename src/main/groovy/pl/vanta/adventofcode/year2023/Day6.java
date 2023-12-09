package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day6 implements ParserSolver<List<List<Integer>>, Integer> {

    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public List<List<Integer>> parse(String lines) {
        var split = lines.split("\n");

        var times = split[0].substring(10).trim().split("\\s+");
        var dists = split[1].substring(10).trim().split("\\s+");

        var timesInt = Stream.of(times)
                .map(Integer::parseInt)
                .toList();

        var distsInt = Stream.of(dists)
                .map(Integer::parseInt)
                .toList();

        return List.of(timesInt, distsInt);
    }

    @Override
    public Integer solve(List<List<Integer>> parsedInput) {
        var times = parsedInput.get(0);
        var dists = parsedInput.get(1);

        var wins = new ArrayList<Integer>();

        for (int j = 0; j < times.size(); j++) {
            int time = times.get(j);
            var dist = dists.get(j);

            var winning = 0;
            for (int speed = 0; speed <= time; speed++) {
                var tmpDist = (time - speed) * speed;

                if (tmpDist > dist) {
                    winning++;
                }
            }

            wins.add(winning);
        }

        return wins.stream().reduce(1, (a, b) -> a * b);
    }

    @Override
    public Integer solve2(List<List<Integer>> parsedInput) {

        return -132432;
    }
}
