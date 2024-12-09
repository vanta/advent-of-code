package pl.vanta.adventofcode.year2024;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.countMatches;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.reverse;

public class Day9 implements ParserSolver<String, Integer> {

    @Override
    public int getDayNumber() {
        return 9;
    }

    @Override
    public String parse(String lines) {
        return lines.replace("\\n", "").trim();
    }

    @Override
    public Integer solve(String parsedInput) {
        var mapped = map(parsedInput);

        var rearranged = rearrange(mapped);

        return checksum(rearranged);
    }

    private String rearrange(String mapped) {
        var result = new StringBuilder(mapped);
        var reversed = reverse(mapped).replace(".", "");

        var dots = countMatches(mapped, ".");
        var digits = mapped.length() - dots;

        var changed = 0;

        for (int i = 0; i < digits; i++) {
            if (result.charAt(i) == '.') {
                result.setCharAt(i, reversed.charAt(changed++));
            }
        }
        result.setLength(digits);

        return result.toString();
    }

    private int checksum(String rearranged) {
        int result = 0;

        for (int i = 0; i < rearranged.length(); i++) {
            if (rearranged.charAt(i) != '.') {
                result += i * parseInt(String.valueOf(rearranged.charAt(i)));
            }
        }

        return result;
    }

    private static String map(String parsedInput) {
        var result = new StringBuilder();

        var files = 0;
        for (int i = 0; i < parsedInput.length(); i++) {
            var c = parsedInput.charAt(i);
            var v = parseInt(String.valueOf(c));

            if (i % 2 == 0) {
                result.append(repeat(String.valueOf(files++), v));
            } else {
                result.append(repeat('.', v));
            }
        }

        return result.toString();
    }

    @Override
    public Integer solve2(String parsedInput) {
        return 0;
    }

}
