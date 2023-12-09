package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

public class Day7 implements ParserSolver<List<Day7.Line>, Integer> {

    @Override
    public int getDayNumber() {
        return 7;
    }

    @Override
    public List<Line> parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(Day7::parseLine)
                .toList();
    }

    private static Line parseLine(String line) {
        var split = line.split(" ");

        var cards = split[0].trim().codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(s -> "C" + s)
                .map(Card::valueOf)
                .toList();

        return new Line(new Hand(cards), parseInt(split[1].trim()));
    }

    @Override
    public Integer solve(List<Line> parsedInput) {
        var index = new AtomicInteger();

        return parsedInput.stream()
                .sorted(comparing(Line::hand))
                .peek(l -> System.out.println(l.hand()))
                .map(Line::bid)
                .reduce(0, (a, b) -> a + (b * index.incrementAndGet()));
    }

    @Override
    public Integer solve2(List<Line> parsedInput) {
        return -1;
    }

    record Line(Hand hand, int bid) {
    }

    record Hand(List<Card> cards) implements Comparable<Hand> {
        @Override
        public int compareTo(Hand o) {
            var tmp1 = points(cards);
            var tmp2 = points(o.cards());

            if (tmp1 != tmp2) {
                return (int) (tmp1 - tmp2);
            }

            for (int i = 0; i < cards.size(); i++) {
                var elem1 = cards.get(i);
                var elem2 = o.cards().get(i);

                if (elem1.compareTo(elem2) != 0) {
                    return elem1.compareTo(elem2);
                }
            }

            return 0;
        }

        private long points(List<Card> cards) {
            return cards.stream()
                    .collect(groupingBy(identity(), counting()))
                    .values().stream()
                    .reduce(0L, (a, b) -> a + (b * b));
        }

        @Override
        public String toString() {
            return cards.stream()
                    .map(c -> c.name().substring(1))
                    .collect(joining());
        }
    }

    enum Card {
        C2, C3, C4, C5, C6, C7, C8, C9, CT, CJ, CQ, CK, CA,
    }
}
