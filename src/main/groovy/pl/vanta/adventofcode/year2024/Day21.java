package pl.vanta.adventofcode.year2024;

import java.util.List;
import java.util.Set;

import pl.vanta.adventofcode.ParserSolver;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

public class Day21 implements ParserSolver<List<String>, Integer> {

    @Override
    public int getDayNumber() {
        return 21;
    }

    @Override
    public List<String> parse(String lines) {
        return stream(lines.split("\n"))
                .toList();
    }

    @Override
    public Integer solve(List<String> input) {
        return input.stream()
                .map(s -> complexity(s) * parseInt(s.substring(0, s.length() - 1)))
                .reduce(0, Integer::sum);
    }

    private int complexity(String s) {
        var numericKeypad = new Keypad(Set.of(
                new Key(0,0, '7'), new Key(0,1, '8'), new Key(0,2, '9'),
                new Key(1,0, '4'), new Key(1,1, '5'), new Key(1,2, '6'),
                new Key(2,0, '1'), new Key(2,1, '2'), new Key(2,2, '3'),
                                   new Key(3,1, '0'), new Key(3,2, 'A')
        ));

        var directionalKeypad = new Keypad(Set.of(
                new Key(0, 1, '^'), new Key( 0, 2, 'A'),
                new Key(1, 0, '<'), new Key(1, 1, 'v'), new Key(1, 2, '>')
        ));



        return 1;
    }

    @Override
    public Integer solve2(List<String> input) {

        return 0;
    }

    private record Key (int x, int y, char value) {

    }

    private record Keypad (Set<Key> keys) {

    }
}
