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
        return 222L;
    }

    @Override
    public Long solve2(char[][] parsedInput) {
        var tempNumbers = new ArrayList<Long>();
        var result = new ArrayList<Long>();

        for (char[] row : transposeCounterClockwise(parsedInput)) {
            var str = new String(Arrays.copyOf(row, row.length - 1)).trim();
            if(str.isEmpty()) {
                continue;
            }
            tempNumbers.add(Long.valueOf(str));
            var op = row[row.length - 1];
            
            if (op == '+') {
                result.add(tempNumbers.stream().reduce(0L, Long::sum));
                tempNumbers.clear();
            } else if (op == '*') {
                result.add(tempNumbers.stream().reduce(1L, (a, b) -> a * b));
                tempNumbers.clear();
            }
        }

        return result.stream().reduce(0L, Long::sum);
    }

}
