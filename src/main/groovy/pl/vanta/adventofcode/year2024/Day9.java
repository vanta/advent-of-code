package pl.vanta.adventofcode.year2024;

import java.util.ArrayList;
import java.util.List;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Collections.swap;

public class Day9 implements ParserSolver<String, Long> {

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public String parse(String lines) {
        return lines.replace("\\n", "").trim();
    }

    @Override
    public Long solve(String parsedInput) {
        var mapped = map(parsedInput);

        var rearranged = rearrange(mapped);

        return checksum(rearranged);
    }

    private static List<Integer> map(String parsedInput) {
        var result = new ArrayList<Integer>();

        var files = 0;

        for (int i = 0; i < parsedInput.length(); i++) {
            var c = parsedInput.charAt(i);
            var v = parseInt(String.valueOf(c));
            var parity = i % 2;

            for (int j = 0; j < v; j++) {
                if (parity == 0) {
                    result.add(files);
                } else {
                    result.add(null);
                }
            }

            if (parity == 0) {
                files++;
            }
        }

        return result;
    }

    private List<Integer> rearrange(List<Integer> mapped) {
        var result = new ArrayList<>(mapped);

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == null) {
                var lastNumberIndex = findLastNumberIndex(result);

                if (lastNumberIndex < i) {
                    break;
                }

                swap(result, i, lastNumberIndex);
            }
        }

        return result;
    }

    private int findLastNumberIndex(List<Integer> mapped) {
        for (int i = mapped.size() - 1; i >= 0; i--) {
            if (mapped.get(i) != null) {
                return i;
            }
        }

        return -1;
    }

    private long checksum(List<Integer> rearranged) {
        long result = 0;

        for (int i = 0; i < rearranged.size(); i++) {
            var value = rearranged.get(i);
            if (value != null) {
                result += i * value;
            }
        }

        return result;
    }

    @Override
    public Long solve2(String parsedInput) {
        return 0L;
    }

}
