package pl.vanta.adventofcode.year2023;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Math.min;
import static pl.vanta.adventofcode.Utils.transposeClockwise;

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
                .mapToInt(s -> getNumber(s, 0))
                .sum();
    }

    @Override
    public Integer solve2(List<char[][]> parsedInput) {
        return parsedInput.stream()
                .peek(Day13::print)
                .mapToInt(s -> getNumber(s, 1))
                .sum();
    }

    private int getNumber(char[][] s, int differentBy) {
        var rows = getRows(s, differentBy);

        if (rows > 0) {
            return 100 * rows;
        } else {
            return getRows(transposeClockwise(s), differentBy);
        }
    }

    private int getRows(char[][] array, int differentBy) {
//        print(array);
        char[] prev = array[0];

        for (int i = 1; i < array.length; i++) {
            char[] curr = array[i];
            int counter = 0;

            counter += countDifferentChars(curr, prev);

            if (counter <= differentBy) {
                for (int j = 0; j < min(i - 1, array.length - i - 1); j++) {
                    counter += countDifferentChars(array[i - j - 2], array[i + j + 1]);
                }

                if(counter == differentBy) {
                    return i;
                }
            }

            prev = curr;
        }

        return 0;
    }

    private static int countDifferentChars(char[] arr1, char[] arr2) {
        int counter = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                counter++;
            }
        }

        return counter;
    }

    private static void print(char[][] array) {
        for (char[] chars : array) {
            System.out.println(Arrays.toString(chars));
        }
    }

}
