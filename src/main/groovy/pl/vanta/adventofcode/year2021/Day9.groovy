package pl.vanta.adventofcode.year2021

class Day9 {

    static int[][] parse(String input) {
        input.split('\n')
                .collect { it.split('').collect { it as int }.toArray() }
                .toArray()
    }

    static int solve(int[][] input) {
        println("Input=$input")

        int result = 0

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (isLowest(input, i, j)) {
                    result += input[i][j]
                    result += 1
                }
            }
        }

        result
    }

    static long solve2(int[][] input) {
        def lowest = []

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (isLowest(input, i, j)) {
                    lowest << [i, j]
                }
            }
        }

        def basinsSizes = lowest
                .collect { findBasinSize(it[0], it[1], input, []) }
                .sort()
                .reverse()

        basinsSizes[0] * basinsSizes[1] * basinsSizes[2]
    }

    static boolean isLowest(int[][] input, int i, int j) {
        int current = input[i][j]

        if (i > 0 && current >= input[i - 1][j]) {
            return false
        }
        if (j > 0 && current >= input[i][j - 1]) {
            return false
        }
        if (i < input.length - 1 && current >= input[i + 1][j]) {
            return false
        }
        if (j < input[i].length - 1 && current >= input[i][j + 1]) {
            return false
        }

        return true
    }

    static int findBasinSize(int i, int j, int[][] input, def basin) {
        int current = input[i][j]
        basin << [i, j]

        int result = 0

        result += checkLocation(i - 1, j, current, input, basin)
        result += checkLocation(i, j - 1, current, input, basin)
        result += checkLocation(i + 1, j, current, input, basin)
        result += checkLocation(i, j + 1, current, input, basin)

        result + 1
    }

    static int checkLocation(int i, int j, int current, int[][] input, def basin) {
        if (i >= 0 && j >= 0 && i < input.length && j < input[i].length
                && current < input[i][j]
                && input[i][j] < 9
                && !basin.contains([i, j])) {
            
            return findBasinSize(i, j, input, basin)
        }

        return 0
    }
}
