package pl.vanta.adventofcode.year2025;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;

import static java.lang.Integer.parseInt;
import static java.lang.Math.*;
import static java.util.Arrays.stream;

public class Day8 extends BaseDay<List<Triple<Integer, Integer, Integer>>, Long> {
    @Override
    public int getDayNumber() {
        return 8;
    }

    @Override
    public List<Triple<Integer, Integer, Integer>> parse(String input) {
        return stream(input.split("\n"))
                .map(l -> l.split(","))
                .map(a -> Triple.of(parseInt(a[0]), parseInt(a[1]), parseInt(a[2])))
                .toList();
    }

    @Override
    public Long solve(List<Triple<Integer, Integer, Integer>> parsedInput) {
                
        
        
        return 0L;
    }

    @Override
    public Long solve2(List<Triple<Integer, Integer, Integer>> parsedInput) {


        return 0L;
    }

    private static long distance(Triple<Integer, Integer, Integer> p1, Triple<Integer, Integer, Integer> p2) {
        return powExact(p1.getLeft() - p2.getLeft(), 2)
                + powExact(p1.getMiddle() - p2.getMiddle(), 2)
                + powExact(p1.getRight() - p2.getRight(), 2);
    }
}
