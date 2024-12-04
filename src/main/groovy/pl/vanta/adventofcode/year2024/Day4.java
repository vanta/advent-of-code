package pl.vanta.adventofcode.year2024;

import java.util.Arrays;

import pl.vanta.adventofcode.ParserSolver;

public class Day4 implements ParserSolver<char[][], Integer> {

    private static final String XMAS = "XMAS";

    @Override
    public int getDayNumber() {
        return 4;
    }

    @Override
    public char[][] parse(String lines) {
        return Arrays.stream(lines.split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        int count = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                count += search(i, j, new Direction(1, 0), parsedInput);
                count += search(i, j, new Direction(0, 1), parsedInput);
                count += search(i, j, new Direction(1, 1), parsedInput);
                count += search(i, j, new Direction(1, -1), parsedInput);
                count += search(i, j, new Direction(-1, 1), parsedInput);
                count += search(i, j, new Direction(-1, -1), parsedInput);
                count += search(i, j, new Direction(-1, 0), parsedInput);
                count += search(i, j, new Direction(0, -1), parsedInput);
            }
        }

        return count;
    }

    private int search(int x, int y, Direction direction, char[][] parsedInput) {
        var l1 = checkLetter(x, y, parsedInput, 'X');
        var l2 = checkLetter(x + direction.x, y + direction.y, parsedInput, 'M');
        var l3 = checkLetter(x + direction.x + direction.x, y + direction.y + direction.y, parsedInput, 'A');
        var l4 = checkLetter(x + direction.x + direction.x + direction.x, y + direction.y + direction.y + direction.y, parsedInput, 'S');

        return l1 && l2 && l3 && l4 ? 1 : 0;
    }

    private boolean checkLetter(int x, int y, char[][] parsedInput, char expected) {
        if (x < 0 || y < 0 || x >= parsedInput.length || y >= parsedInput[x].length) {
            return false;
        }

        return parsedInput[x][y] == expected;
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        int count = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            for (int j = 0; j < parsedInput[i].length; j++) {
                if (parsedInput[i][j] == 'A') {
                    if (checkXmas(i, j, parsedInput)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean checkXmas(int x, int y, char[][] parsedInput) {
        if (x < 1 || y < 1 || x == parsedInput.length - 1 || y == parsedInput[x].length - 1) {
            return false;
        }

        return
                ((parsedInput[x - 1][y - 1] == 'M' && parsedInput[x + 1][y + 1] == 'S')
                || (parsedInput[x - 1][y - 1] == 'S' && parsedInput[x + 1][y + 1] == 'M'))
                &&
                ((parsedInput[x - 1][y + 1] == 'M' && parsedInput[x + 1][y - 1] == 'S')
                        || (parsedInput[x - 1][y + 1] == 'S' && parsedInput[x + 1][y - 1] == 'M'));
    }

    private record Direction(int x, int y) {
    }
}
