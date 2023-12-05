package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day4 implements ParserSolver<List<Day4.Card>, Integer> {

    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public List<Day4.Card> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day4::parseLine)
                .toList();
    }

    private static Card parseLine(String l) {
        return new Card(parseInt(l.substring(5, l.indexOf(":"))));
    }

    @Override
    public Integer solve(List<Day4.Card> parsedInput) {
        return -1;
    }

    @Override
    public Integer solve2(List<Day4.Card> parsedInput) {
        return -1;
    }

    record Card(int id) {
    }
}
