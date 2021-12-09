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


        0
    }

    static boolean isLowest(int[][] ints, int i, int j) {
        if (i > 0 && ints[i][j] >= ints[i - 1][j]) {
            return false
        }
        if (j > 0 && ints[i][j] >= ints[i][j - 1]) {
            return false
        }
        if (i < ints.length - 1 && ints[i][j] >= ints[i + 1][j]) {
            return false
        }
        if (j < ints[i].length - 1 && ints[i][j] >= ints[i][j + 1]) {
            return false
        }

        return true
    }
}
