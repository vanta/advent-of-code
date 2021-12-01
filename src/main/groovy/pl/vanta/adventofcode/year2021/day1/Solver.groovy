package pl.vanta.adventofcode.year2021.day1

class Solver {
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

    static int solve2(List<Integer> input) {
        int counter = 0
        int result = 0

        if (input.size() < 4) {
            0
        } else {
            def previous = input[counter]

            while (counter++ < input.size() - 3) {
                def current1 = input[counter]
                def current2 = input[counter + 1]
                def current3 = input[counter + 2]

                def sumPrevious = previous + current1 + current2
                def sumCurrent = current1 + current2 + current3

                if (sumCurrent > sumPrevious) {
                    result++
                }

                previous = current1
            }

            result
        }
    }
}
