package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pl.vanta.adventofcode.Location;
import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.*;

public class Day18 implements ParserSolver<Set<Location>, Integer> {

    @Override
    public int getDayNumber() {
        return 18;
    }

    @Override
    public Set<Location> parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(s -> s.split(","))
                .map(s -> new Location(parseInt(s[0]), parseInt(s[1])))
                .collect(toSet());
    }

    @Override
    public Integer solve(Set<Location> input) {


        var result = traverse(new Location(0, 0), new Location(6,6), input, new ArrayList<>(), new HashSet<>());

        return 0;
    }

    private List<Location> traverse(Location current, Location end, Set<Location> input, List<Location> path, Set<Location> visited) {
        if(!visited.add(current)) {
            return path;
        }

        if(current.equals(end)) {
            path.add(current);
            return path;
        }



        return null;
    }

    @Override
    public Integer solve2(Set<Location> input) {
        return 0;
    }

}
