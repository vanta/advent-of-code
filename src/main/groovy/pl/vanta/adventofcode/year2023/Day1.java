package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.stream.Stream;

public class Day1 implements ParserSolver<List<String>, Integer> {
    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<String> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> parsedInput) {
        return parsedInput.stream()
                .map(s -> {
                    var trimmed = s.replaceFirst("^[a-z]*", "")
                            .replaceFirst("[a-z]*$", "");


                    return trimmed.charAt(0) + trimmed.substring(trimmed.length() - 1);
                })
                .map(s -> Integer.parseInt(s, 10))
                .reduce(0, Integer::sum);
    }

    @Override
    public Integer solve2(List<String> parsedInput) {
        return null;
    }
}
