package pl.vanta.adventofcode.year2025;

import java.util.ArrayList;
import java.util.Arrays;

import static pl.vanta.adventofcode.Utils.transposeCounterClockwise;

public class Day6 extends BaseDay<char[][], Long> {
    @Override
    public int getDayNumber() {
        return 6;
    }

    @Override
    public char[][] parse(String input) {
        return Arrays.stream(input.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Long solve(char[][] parsedInput) {
        var numbers = new long[parsedInput.length - 1][];
        var operations = new String(parsedInput[parsedInput.length - 1]).chars()
                .filter(c -> c != ' ')
                .mapToObj(c -> (char) c)
                .toArray();

        for (int i = 0; i < parsedInput.length - 1; i++) {
            var rowNumbers = Arrays.stream(new String(parsedInput[i]).trim().split("\\s+"))
                    .mapToLong(Long::valueOf)
                    .toArray();

            numbers[i] = rowNumbers;
        }

        long result = 0L;
        for (int i = 0; i < operations.length; i++) {
            char op = (char) operations[i];
            long temp = 0L;
            if (op == '+') {
                for (long[] row : numbers) {
                    temp += row[i];
                }
            } else if (op == '*') {
                temp = 1L;
                for (long[] row : numbers) {
                    temp *= row[i];
                }
            }
            result += temp;
        }

        return result;
    }

    @Override
    public Long solve2(char[][] parsedInput) {
        var tempNumbers = new ArrayList<Long>();
        var result = new ArrayList<Long>();

        for (char[] row : transposeCounterClockwise(parsedInput)) {
            var str = new String(Arrays.copyOf(row, row.length - 1)).trim();
            if (str.isEmpty()) {
                tempNumbers.clear();
                continue;
            }
            tempNumbers.add(Long.valueOf(str));
            var op = row[row.length - 1];

            if (op == '+') {
                result.add(tempNumbers.stream().reduce(0L, Long::sum));
            } else if (op == '*') {
                result.add(tempNumbers.stream().reduce(1L, (a, b) -> a * b));
            }
        }

        return result.stream().reduce(0L, Long::sum);
    }

}
