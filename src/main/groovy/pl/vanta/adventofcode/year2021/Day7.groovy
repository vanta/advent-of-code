package pl.vanta.adventofcode.year2021


import static java.lang.Integer.MAX_VALUE
import static java.lang.Math.abs

class Day7 {

    static List<Integer> parse(String input) {
        input.split(',').collect { it as int }
    }

    static int solve(List<Integer> positions) {
        println "Input size=${positions.size()}"

        def max = positions.max()
        def minFuel = MAX_VALUE

        for (int position = 0; position <= max; position++) {
            def fuel = checkPosition(position, positions)

            if (fuel < minFuel) {
                minFuel = fuel
            }
        }

        minFuel
    }

    static int checkPosition(int position, List<Integer> positions) {
        int fuel = 0
        for (int number : positions) {
            fuel += abs(number - position)
        }
        fuel
    }

    static long solve2(List<Integer> positions) {
        println "Input size=${positions.size()}"

        def max = positions.max()
        def minFuel = MAX_VALUE

        for (int position = 0; position <= max; position++) {
            def fuel = checkPosition2(position, positions)

            if (fuel < minFuel) {
                minFuel = fuel
            }
        }

        minFuel
    }

    static int checkPosition2(int position, List<Integer> positions) {
        int fuel = 0
        for (int p : positions) {
            fuel += sumFuel(abs(p - position))
        }
        fuel
    }

    static int sumFuel(int n) {
        n * (n + 1) / 2
    }
}
