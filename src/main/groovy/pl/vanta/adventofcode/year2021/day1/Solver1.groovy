package pl.vanta.adventofcode.year2021.day1

class Solver1 {
    static List<Integer> parse(String input) {
        input
                .split("\n")
                .collect { it.toInteger() }
    }

    static int solve(List<Integer> input) {
        int counter = 0
        int result = 0

        if (input.size() < 2) {
            0
        } else {
            def previous = input[counter]

            while (counter < input.size()) {
                def current = input[++counter]

                if (current > previous) {
                    result++
                }

                previous = current
            }

            result
        }
    }
}
