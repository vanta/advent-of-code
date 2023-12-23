package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Day18 implements ParserSolver<List<Day18.Dig>, Integer> {

    @Override
    public int getDayNumber() {
        return 18;
    }

    @Override
    public List<Day18.Dig> parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(this::parseLine)
                .toList();
    }

    private Dig parseLine(String s) {
        Scanner scanner = new Scanner(s);
        return new Dig(scanner.next().charAt(0), scanner.nextInt());
    }

    @Override
    public Integer solve(List<Day18.Dig> parsedInput) {
        parsedInput.forEach(System.out::println);

        int w = max(parsedInput.stream().filter(d -> d.dir == 'R' || d.dir == 'L'), 'R');
        int h = max(parsedInput.stream().filter(d -> d.dir == 'U' || d.dir == 'D'), 'D');


        return 0;
    }

    private static int max(Stream<Dig> parsedInput, char c) {
        int max = 0;
        int curr = 0;
        for (Dig dig : parsedInput.toList()) {
            curr += dig.dir == c ? dig.meters : -dig.meters;
            max = Math.max(max, curr);
        }
        return max + 1;
    }

    @Override
    public Integer solve2(List<Day18.Dig> parsedInput) {
        return 0;
    }

    public record Dig(char dir, int meters) {
    }

}
