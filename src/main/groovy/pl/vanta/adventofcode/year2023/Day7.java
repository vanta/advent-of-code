package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static pl.vanta.adventofcode.year2023.Day7.Card.CJ;

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
        Comparator<Line> c1 = comparing(l -> l.hand().getPoints());
        Comparator<Line> c2 = (l1, l2) -> compareCards(l1.hand(), l2.hand());
        return calculatePoints(parsedInput, c1, c2);
    }

    @Override
    public Integer solve2(List<Line> parsedInput) {
        Comparator<Line> c1 = comparing(l -> l.hand().getPointsWithJoker());
        Comparator<Line> c2 = (l1, l2) -> compareCardsWithJoker(l1.hand(), l2.hand());
        return calculatePoints(parsedInput, c1, c2);
    }

    private Integer calculatePoints(List<Line> parsedInput, Comparator<Line> mainComparator, Comparator<Line> seconComparator) {
        var index = new AtomicInteger();
        return parsedInput.stream()
                .sorted(mainComparator.thenComparing(seconComparator))
                .peek(l -> System.out.println(l.hand()))
                .map(Line::bid)
                .reduce(0, (a, b) -> a + (b * index.incrementAndGet()));
    }

    private int compareCards(Hand h1, Hand h2) {
        for (int i = 0; i < h1.cards().size(); i++) {
            var elem1 = h1.cards().get(i);
            var elem2 = h2.cards().get(i);

            if (elem1.compareTo(elem2) != 0) {
                return elem1.compareTo(elem2);
            }
        }

        return 0;
    }

    private int compareCardsWithJoker(Hand h1, Hand h2) {
        for (int i = 0; i < h1.cards().size(); i++) {
            var elem1 = h1.cards().get(i);
            var elem2 = h2.cards().get(i);

            if (elem1 != elem2) {
                if (elem1 == CJ) {
                    return -1;
                }
                if (elem2 == CJ) {
                    return 1;
                }

                return elem1.compareTo(elem2);
            }
        }

        return 0;
    }

    record Line(Hand hand, int bid) {
    }

    record Hand(List<Card> cards) {
        @Override
        public String toString() {
            return cards.stream()
                    .map(c -> c.name().substring(1))
                    .collect(joining());
        }

        private long getPoints() {
            return calculatePoints(occurrences());
        }

        private long getPointsWithJoker() {
            var occ = occurrences();

            var jokerPoints = occ.getOrDefault(CJ, 0L);
            occ.remove(CJ);

            var maxEntry = occ.entrySet().stream()
                    .max(comparingByValue())
                    .orElse(Map.entry(CJ, 0L));

            occ.put(maxEntry.getKey(), maxEntry.getValue() + jokerPoints);

            return calculatePoints(occ);
        }

        private long calculatePoints(Map<Card, Long> occurrences) {
            return occurrences
                    .values().stream()
                    .reduce(0L, (a, b) -> a + (b * b));
        }

        private Map<Card, Long> occurrences() {
            return cards.stream()
                    .collect(groupingBy(identity(), counting()));
        }
    }

    enum Card {
        C2, C3, C4, C5, C6, C7, C8, C9, CT, CJ, CQ, CK, CA,
    }
}
