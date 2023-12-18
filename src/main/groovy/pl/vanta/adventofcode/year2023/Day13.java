package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class Day13 implements ParserSolver<List<List<String>>, Integer> {

    @Override
    public int getDayNumber() {
        return 13;
    }

    @Override
    public List<List<String>> parse(String lines) {
        return Stream.of(lines.split("\n\n"))
                .map(Day13::parseRow)
                .toList();
    }

    private static List<String> parseRow(String s) {
        return Stream.of(s.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<List<String>> parsedInput) {
        return parsedInput.stream()
                .mapToInt(s -> {
                    var rows = getRows(s);
                    var cols = getCols(s);
                    var i = 100 * rows + cols;

                    if(i == 0) {
                        s.forEach(System.out::println);
                        System.out.println("----------------");
                    }
                    return i;
                })
                .sum();
    }

    private int getCols(List<String> strings) {
        return getRows(transpose(strings));
    }

    private int getRows(List<String> strings) {
        String prev = null;
        for (int i = 0; i < strings.size(); i++) {
            String curr = strings.get(i);
            if (curr.equals(prev)) {
                for (int j = 0; j < min(i - 1, strings.size() - i - 1); j++) {
                    if (!strings.get(i - j - 2).equals(strings.get(i + j + 1))) {
                        return 0;
                    }
                }

                return i;
            }

            prev = curr;
        }

        return 0;
    }

    private static List<String> transpose(List<String> strings) {
        var len = strings.get(0).length();
        var result = new ArrayList<StringBuilder>();

        for (int i = 0; i < len; i++) {
            result.add(new StringBuilder());
        }

        for (int i = 0; i < len; i++) {
            for (String string : strings) {
                result.get(i).append(string.charAt(i));
            }
        }
        return result.stream().map(StringBuilder::toString).toList();
    }

    @Override
    public Integer solve2(List<List<String>> parsedInput) {
        return -1;
    }

}
