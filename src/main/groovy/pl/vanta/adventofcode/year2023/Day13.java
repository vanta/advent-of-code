package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class Day13 implements ParserSolver<List<char[][]>, Integer> {

    @Override
    public int getDayNumber() {
        return 13;
    }

    @Override
    public List<char[][]> parse(String lines) {
        return Stream.of(lines.split("\n\n"))
                .map(Day13::parseRow)
                .toList();
    }

    private static char[][] parseRow(String s) {
        return Stream.of(s.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(List<char[][]> parsedInput) {
        return parsedInput.stream()
//                .peek(Day13::print)
                .mapToInt(this::getNumber)
                .sum();
    }

    @Override
    public Integer solve2(List<char[][]> parsedInput) {
        return -1;
    }

    private int getNumber(char[][] s) {
        var rows = getRows(s);

        if(rows > 0) {
            return 100 * rows;
        } else {
            return getRows(transpose(s));
        }
    }

    private int getRows(char[][] array) {
//        print(array);
        char[] prev = null;
        label:
        for (int i = 0; i < array.length; i++) {
            char[] curr = array[i];
            if (Arrays.equals(curr, prev)) {
                for (int j = 0; j < min(i - 1, array.length - i - 1); j++) {
                    if (!Arrays.equals(array[i - j - 2], array[i + j + 1])) {
                        continue label;
                    }
                }

                return i;
            }

            prev = curr;
        }

        return 0;
    }

    private static char[][] transpose(char[][] array) {
        var result = new char[array[0].length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                result[j][i] = array[i][j];
            }
        }

        return result;
    }

    private static void print(char[][] array) {
        for (char[] chars : array) {
            System.out.println(Arrays.toString(chars));
        }
    }

}
