package pl.vanta.adventofcode.year2023;

import pl.vanta.adventofcode.ParserSolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day16 implements ParserSolver<char[][], Integer> {

    @Override
    public int getDayNumber() {
        return 16;
    }

    @Override
    public char[][] parse(String lines) {
        return Stream.of(lines.trim().split("\n"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @Override
    public Integer solve(char[][] parsedInput) {
        return findBestStartingMove(parsedInput, List.of(new Move(0, 0, 0, 1)));
    }

    @Override
    public Integer solve2(char[][] parsedInput) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < parsedInput.length; i++) {
            moves.add(new Move(i, 0, 0, 1));
            moves.add(new Move(i, parsedInput.length - 1, 0, -1));
        }

        for (int j = 0; j < parsedInput[0].length; j++) {
            moves.add(new Move(0, j, 1, 0));
            moves.add(new Move(parsedInput[0].length - 1, j, -1, 0));
        }

        return findBestStartingMove(parsedInput, moves);
    }

    private int findBestStartingMove(char[][] parsedInput, List<Move> moves) {
        System.out.println("Moves: " + moves.size());

        return moves.stream()
                .map(m -> traverse(parsedInput, new HashSet<>(), m))
                .map(Day16::count)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private static int count(Set<Move> visited) {
        return (int) visited.stream()
                .map(Move::resetToPoint)
                .distinct()
                .count();
    }

    private Set<Move> traverse(char[][] parsedInput, Set<Move> visited, Move start) {
        if (!insideOfArray(parsedInput, start.x, start.y)) {
            return visited;
        }

//        System.out.println("Traversing: " + start.x + " " + start.y + " " + start.dx + " " + start.dy);

        Move move = start;

        while (insideOfArray(parsedInput, move.x, move.y)) {
            if (visited.contains(move)) {
                return visited;
            }

            visited.add(move);

            var c = parsedInput[move.x][move.y];

//            System.out.println("(" + move.x + "," + move.y + ") " + c);

            if (c == '.') {
                move = move.move();
            } else if (c == '-') {
                if (move.dx == 0) {
                    move = move.move();
                } else {
//                    System.out.println("Horizontal split in: " + move);
                    var split = move.splitHorizontally();
                    visited.addAll(traverse(parsedInput, visited, split[0]));
                    visited.addAll(traverse(parsedInput, visited, split[1]));
                    return visited;
                }
            } else if (c == '|') {
                if (move.dy == 0) {
                    move = move.move();
                } else {
//                    System.out.println("Vertical split in: " + move);
                    var split = move.splitVertically();
                    visited.addAll(traverse(parsedInput, visited, split[0]));
                    visited.addAll(traverse(parsedInput, visited, split[1]));
                    return visited;
                }
            } else if (c == '/') {
                if (move.dx == 0) {
                    move = move.leftUpRightDown();
                } else {
                    move = move.upRightDownLeft();
                }
            } else if (c == '\\') {
                if (move.dx == 0) {
                    move = move.leftDownRightUp();
                } else {
                    move = move.upLeftDownRight();
                }
            }
        }

        return visited;
    }

    private static boolean insideOfArray(char[][] parsedInput, int startX, int startY) {
        return startX >= 0 && startX < parsedInput.length && startY >= 0 && startY < parsedInput[0].length;
    }

    record Move(int x, int y, int dx, int dy) {
        Move move() {
            return new Move(x + dx, y + dy, dx, dy);
        }

        Move leftUpRightDown() {
            return new Move(x - dy, y, -dy, 0);
        }

        Move upRightDownLeft() {
            return new Move(x, y - dx, 0, -dx);
        }

        Move leftDownRightUp() {
            return new Move(x + dy, y, dy, 0);
        }

        Move upLeftDownRight() {
            return new Move(x, y + dx, 0, dx);
        }

        Move resetToPoint() {
            return new Move(x, y, 0, 0);
        }

        Move[] splitHorizontally() {
            return new Move[]{
                    new Move(x, y - 1, 0, -1),
                    new Move(x, y + 1, 0, 1)
            };
        }

        Move[] splitVertically() {
            return new Move[]{
                    new Move(x - 1, y, -1, 0),
                    new Move(x + 1, y, 1, 0)
            };
        }
    }
}
