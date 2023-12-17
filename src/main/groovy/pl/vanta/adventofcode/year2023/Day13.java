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
//        parsedInput.forEach(strings -> strings.forEach(System.out::println));

        var rows = parsedInput.stream()
                .map(this::getRows)
                .reduce(0, Integer::sum);
        var cols = parsedInput.stream()
                .map(this::getCols)
                .reduce(0, Integer::sum);
        return rows * 100 + cols;
    }

    private int getCols(List<String> strings) {
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

        return getRows(result.stream().map(StringBuilder::toString).toList());
    }

    private int getRows(List<String> strings) {
        String prev = null;
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (s.equals(prev)) {
                return check(strings, i);
            }

            prev = s;
        }

        return 0;
    }

    private int check(List<String> strings, int i) {
        var l1 = new ArrayList<>(strings.subList(0, i));
        reverse(l1);
        var l2 = strings.subList(i, strings.size());

        var bigger = l1.size() > l2.size() ? l1 : l2;
        var smaller = l1.size() > l2.size() ? l2 : l1;

        var tmp = bigger.subList(0, smaller.size());
        if (tmp.equals(smaller)) {
            return bigger.size();
        }

        return 0;
    }

    @Override
    public Integer solve2(List<List<String>> parsedInput) {
        return -1;
    }

}
