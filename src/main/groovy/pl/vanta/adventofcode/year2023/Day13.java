package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.reverse;

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
                .mapToInt(s -> 100 * getRows(s) + getCols(s))
                .sum();
    }

    private int getCols(List<String> strings) {
        return getRows(transpose(strings));
    }

    private int getRows(List<String> strings) {
        String prev = null;
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (s.equals(prev)) {
                int elementsToCheck = Math.min(i - 1, strings.size() - i - 1);

                for (int j = 0; j < elementsToCheck; j++) {
                    String first =strings.get(i - j - 2);
                    String second = strings.get(i + j + 1);
                    if (!first.equals(second)) {
                        return 0;
                    }
                }

                return i;
            }

            prev = s;
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

    private int check(List<String> strings, int i) {
        var l1 = new ArrayList<>(strings.subList(0, i));
        reverse(l1);
        var l2 = strings.subList(i, strings.size());

        if(l1.size() > l2.size()) {
            var tmp = l1.subList(0, l2.size());
            if (tmp.equals(l2)) {
                return l1.size();
            }
        } else {
            var tmp = l2.subList(0, l1.size());
            if (tmp.equals(l1)) {
                return l1.size();
            }
        }

        return 0;
    }

    @Override
    public Integer solve2(List<List<String>> parsedInput) {
        return -1;
    }

}
