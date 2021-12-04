package pl.vanta.adventofcode.year2021

class Day4 {
    static Bingo parse(String input) {
        def lines = input.readLines()

        def inputs = lines[0].split(',').collect { it as int }
        def boards = parseBoards(lines)

        new Bingo(numbers: inputs, boards: boards)
    }

    static int solve(Bingo bingo) {
        for (def number : bingo.numbers) {
            for (def board : bingo.boards) {
                def winning = board.mark(number)

                if (winning) {
                    return board.calculateScore(number)
                }
            }
        }

        -1
    }


    static int solve2(Bingo input) {
        -1
    }

    static List<Board> parseBoards(List<String> lines) {
        def boards = new ArrayList<Board>()

        for (int i = 2; i < lines.size(); i = i + 6) {
            boards.add(parseBoard(lines[i], lines[i + 1], lines[i + 2], lines[i + 3], lines[i + 4]))
        }

        boards
    }

    static Board parseBoard(String... lines) {
        def board = new Board()

        for (int i = 0; i < lines.size(); i++) {
            board.numbers[i] = parseLine(lines[i])
        }

        board
    }

    static int[] parseLine(String line) {
        line.trim().split("\\s+").collect { it as int }
    }

    static class Bingo {
        List<Board> boards
        List<Integer> numbers
    }

    static class Board {
        int[][] numbers = new int[5][5]
        boolean[][] marked = new boolean[5][5]

        boolean mark(int number) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (numbers[i][j] == number) {
                        marked[i][j] = true
                    }
                }
            }

            isWinning()
        }

        boolean isWinning() {
            for (int i = 0; i < 5; i++) {
                if (isWinning(marked[i])) {
                    return true
                }
            }

            //todo check columns
        }

        static boolean isWinning(boolean[] row) {
            row.find { !it } == null
        }

        int calculateScore(int number) {
            number * sumUnmarkedNumbers()
        }

        int sumUnmarkedNumbers() {
            int sum = 0
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!marked[i][j]) {
                        sum += numbers[i][j]
                    }
                }
            }
            sum
        }
    }
}
