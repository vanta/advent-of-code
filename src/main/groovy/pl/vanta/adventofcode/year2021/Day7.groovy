package pl.vanta.adventofcode.year2021


import static java.lang.Integer.MAX_VALUE
import static java.lang.Math.abs

class Day7 {

    static List<Integer> parse(String input) {
        input.split(',').collect { it as int }
    }

    static int solve(List<Integer> positions) {
        println "Input size=${positions.size()}"

        def minFuel = MAX_VALUE

        for (int position : positions) {
            def fuel = checkPosition(position, positions)

            if (fuel < minFuel) {
                minFuel = fuel
            }
        }
        
        minFuel
    }

    static int checkPosition(int position, List<Integer> input) {
        int fuel = 0
        for (int number : input) {
            fuel += abs(number - position)
        }
        fuel
    }

    static long solve2(List<Integer> numbers) {

    }

    static int findPosition(List<Integer> numbers) {

    }

    static int calculateFuel(List<Integer> numbers, int position) {
        0
    }
}
