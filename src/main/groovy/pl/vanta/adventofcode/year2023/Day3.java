package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.groupingBy;

public class Day3 implements ParserSolver<char[][], Integer> {

//    private static final String ANSI_RESET = "\u001B[0m";
//    private static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";
//    private static final String ANSI_RED = "\u001B[31m";

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
        var numbers = getNumbers(parsedInput);

//        var list = numbers.stream()
//                .filter(n -> !isSymbolAround(parsedInput, n.x(), n.y(), n.value().length()))
//                .toList();
//
//        for (int i = 0; i < parsedInput.length; i++) {
//            for (int j = 0; j < parsedInput[i].length; j++) {
//                if (isMatch(list, i, j)) {
//                    System.out.print(WHITE_BACKGROUND_BRIGHT);
//                    System.out.print(ANSI_RED);
//                    System.out.print(parsedInput[i][j]);
//                    System.out.print(ANSI_RESET);
//                } else {
//                    System.out.print(parsedInput[i][j]);
//                }
//            }
//            System.out.println();
//        }

        return numbers.stream()
//                .peek(n -> System.out.println(n.value()))
                .map(n -> new NumberWithSymbol(n, getSymbolAround(parsedInput, n.x(), n.y(), n.value().length())))
                .filter(n -> n.symbol().isPresent())
                .map(NumberWithSymbol::number)
                .map(Number::value)
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private static List<Number> getNumbers(char[][] parsedInput) {
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
                    if (j == sizeY - 1) {
                        numbers.add(new Number(x, y, buff.toString()));
                    }
                } else {
                    if (!buff.isEmpty()) {
                        numbers.add(new Number(x, y, buff.toString()));
                        buff = new StringBuilder();
                    }
                }
            }

        }
        return numbers;
    }

//    private static boolean isMatch(List<Number> list, int i, int j) {
//        return list.stream()
//                .anyMatch(n -> (n.x() == i || n.x() - 1 == i || n.x() + 1 == i) && n.y()-1 <= j && n.y() + n.value().length() >= j);
//    }

    private Optional<Symbol> getSymbolAround(char[][] input, int x, int y, int length) {
        List<Symbol> symbols = new ArrayList<>();

        for (int j = -1; j <= length; j++) {
            safeGet(input, x - 1, y + j).ifPresent(symbols::add);
        }
        for (int j = -1; j <= length; j++) {
            safeGet(input, x + 1, y + j).ifPresent(symbols::add);
        }

        safeGet(input, x, y - 1).ifPresent(symbols::add);
        safeGet(input, x, y + length).ifPresent(symbols::add);

        return symbols.stream()
                .filter(s -> !".".equals(s.value()))
                .filter(s -> !"0123456789".contains(s.value()))
                .findFirst();
//
//        return buff.toString().replaceAll("\\.", "").replaceAll("\\d", "");
    }

    private Optional<Symbol> safeGet(char[][] parsedInput, int x, int y) {
        if (x < 0 || y < 0 || x >= parsedInput.length || y >= parsedInput[0].length) {
            return empty();
        }
        return of(new Symbol(x, y, String.valueOf(parsedInput[x][y])));
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        var numbers = getNumbers(parsedInput);

        return numbers.stream()
                .map(n -> new NumberWithSymbol(n, getSymbolAround(parsedInput, n.x(), n.y(), n.value().length())))
                .filter(n -> n.symbol().isPresent())
                .filter(n -> n.symbol().get().value().equals("*"))
                .collect(groupingBy(n -> n.symbol().get()))
                .values().stream()
                .filter(numberWithSymbols -> numberWithSymbols.size() == 2)
                .map(numberWithSymbols -> numberWithSymbols.get(0).number().getValueInt() * numberWithSymbols.get(1).number().getValueInt())
                .reduce(0, Integer::sum);
    }

    private record Number(int x, int y, String value) {
        int getValueInt() {
            return parseInt(value);
        }
    }

    private record Symbol(int x, int y, String value) {
    }

    private record NumberWithSymbol(Number number, Optional<Symbol> symbol) {
    }
}
