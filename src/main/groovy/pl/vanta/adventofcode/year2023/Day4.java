package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day4 implements ParserSolver<List<Day4.Card>, Integer> {

    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public List<Day4.Card> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day4::parseLine)
                .toList();
    }

    private static Card parseLine(String l) {
        var colon = l.indexOf(":");
        var pipe = l.indexOf("|");
        var id = parseInt(l.substring(5, colon).trim());
        var winning = l.substring(colon + 1, pipe).trim().split("\\s+");
        var numbers = l.substring(pipe + 1).trim().split("\\s+");
        return new Card(id, Set.of(winning), Set.of(numbers));
    }

    @Override
    public Integer solve(List<Day4.Card> parsedInput) {
        return parsedInput.stream()
                .map(c -> count(c.winning, c.numbers))
                .map(i -> Math.pow(2, i - 1))
                .mapToInt(Double::intValue)
                .reduce(0, Integer::sum);
    }

    private int count(Set<String> winning, Set<String> numbers) {
        int sizeBefore = numbers.size();
        var tmp = new HashSet<>(numbers);
        tmp.removeAll(winning);

        return sizeBefore - tmp.size();
    }

    @Override
    public Integer solve2(List<Day4.Card> parsedInput) {
        var winning = parsedInput.stream()
                .map(c -> count(c.winning, c.numbers))
                .toArray(Integer[]::new);

        var instances = new int[winning.length];
        Arrays.fill(instances, 1);

        for (int i = 0; i < instances.length; i++) {
            for (int j = 0; j < instances[i]; j++) {
                for (int k = 0; k < winning[i]; k++) {
                    instances[i + k + 1]++;
                }
            }
        }

        return Arrays.stream(instances).sum();
    }

    record Card(int id, Set<String> winning, Set<String> numbers) {
    }
}
