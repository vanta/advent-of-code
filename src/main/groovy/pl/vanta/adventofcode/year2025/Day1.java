package pl.vanta.adventofcode.year2025;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.Math.*;

public class Day1 extends BaseDay<List<Integer>, Integer> {
    @Override
    public int getDayNumber() {
        return 1;
    }

    @Override
    public List<Integer> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(s -> (s.charAt(0) == 'L' ? -1 : 1) * parseInt(s.substring(1)))
                .toList();
    }

    @Override
    public Integer solve(List<Integer> parsedInput) {
        var value = 50;
        var zeros = 0;

        for (Integer move : parsedInput) {
            value = (value + move) % 100;

            if (value == 0) {
                zeros++;
            }
        }

        return zeros;
    }

    @Override
    public Integer solve2(List<Integer> parsedInput) {
        var value = 50;
        var zeros = 0;

        for (Integer move : parsedInput) {
            var newValue = (((value + move) % 100) + 100) % 100;
            var newZeros = (abs(move) / 100);

            if(newValue == 0 && value!=0) {
                newZeros++;
            }

            if(move > 0 && newValue < value && newValue !=0) {
                newZeros++;
            } else if(move < 0 && newValue > value && value !=0) {
                newZeros++;
            }

            System.out.println("%d -> %d = %d, zeros = %d".formatted(value, move, newValue, newZeros));

            value = newValue;
            zeros += newZeros;
        }

        return zeros;
    }

}
