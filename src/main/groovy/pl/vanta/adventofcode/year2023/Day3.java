package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;

public class Day3 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 3;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        List<Number> numbers = new ArrayList<>();

        var sizeX = parsedInput.length;
        for (int i = 0; i < sizeX; i++) {
            var sizeY = parsedInput[i].length;

            int x = -1;
            int y = -1;
            StringBuilder buff = new StringBuilder();

            for (int j = 0; j < sizeY; j++) {
                char c = parsedInput[i][j];

                if (isDigit(c)) {
                    if (buff.isEmpty()) {
                        x = i;
                        y = j;
                    }
                    buff.append(c);
                } else {
                    if (!buff.isEmpty()) {
                        numbers.add(new Number(x, y, buff.toString()));
                        buff = new StringBuilder();
                    }
                }
            }

        }

        return numbers.stream()
                .filter(n -> isSymbolAround(parsedInput, n.x(), n.y(), n.value().length()))
                .map(Number::value)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private boolean isSymbolAround(char[][] input, int x, int y, int length) {
        StringBuilder buff = new StringBuilder();

        for (int j = -1; j <= length; j++) {
            buff.append(safeGet(input, x - 1, y + j));
        }
        for (int j = -1; j <= length; j++) {
            buff.append(safeGet(input, x + 1, y + j));
        }

        buff.append(safeGet(input, x, y - 1));
        buff.append(safeGet(input, x, y + length));

        return !buff.toString().replaceAll("\\.", "").replaceAll("\\d", "").isEmpty();
    }

    private String safeGet(char[][] parsedInput, int x, int y) {
        if (x < 0 || y < 0 || x >= parsedInput.length || y >= parsedInput[0].length) {
            return "";
        }

        return String.valueOf(parsedInput[x][y]);
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        return 0;

    }

    record Number(int x, int y, String value) {
    }
}
