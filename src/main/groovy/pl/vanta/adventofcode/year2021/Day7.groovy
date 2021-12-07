package pl.vanta.adventofcode.year2021


import static java.lang.Integer.MAX_VALUE
import static java.lang.Math.abs

class Day7 {

    static List<Integer> parse(String input) {
        input.split(',').collect { it as int }
    }

    static int solve(List<Integer> positions) {
        calculatePositions(positions) { int a, int b -> sumFuel(a, b) }
    }

    static long solve2(List<Integer> positions) {
        calculatePositions(positions) { int a, int b -> sumFuel2(a, b) }
    }

    private static int calculatePositions(List<Integer> positions, Closure<Integer> cl) {
        println "Input size=${positions.size()}"

        def max = positions.max()
        def minFuel = MAX_VALUE

        for (int position = 0; position <= max; position++) {
            def fuel = checkPosition(position, positions, cl)

            if (fuel < minFuel) {
                minFuel = fuel
            }
        }

        minFuel
    }

    static int checkPosition(int position, List<Integer> positions, Closure<Integer> distanceFunction) {
        int fuel = 0
        for (int number : positions) {
            fuel += distanceFunction.call(position, number)
        }
        fuel
    }

    private static int sumFuel(int a, int b) {
        abs(a - b)
    }

    private static int sumFuel2(int a, int b) {
        int n = abs(a - b)
        n * (n + 1) / 2
    }

}
