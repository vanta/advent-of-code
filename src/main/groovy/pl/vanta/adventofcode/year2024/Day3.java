package pl.vanta.adventofcode.year2024;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;
import pl.vanta.adventofcode.ParserSolver;

public class Day3 implements ParserSolver<String, Integer> {
    private static final Pattern P = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");

    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public String parse(String lines) {
        return lines.trim();
    }

    @Override
    public Integer solve(String parsedInput) {
        var matcher = P.matcher(parsedInput);

        var result = IntStream.iterate(0, i -> matcher.find(), i -> i + 1)
                .mapToObj(i -> matcher.group())
                .map(s -> s.replace("mul(", "").replace(")", ""))
                .map(s -> s.split(","))
                .map(a -> Pair.of(Integer.parseInt(a[0]), Integer.parseInt(a[1])))
                .map(p -> p.getLeft() * p.getRight())
                .reduce(0, Integer::sum);

        return result;
    }

    @Override
    public Integer solve2(String parsedInput) {
        return 0;

    }

}
