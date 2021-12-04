package pl.vanta.adventofcode.year2021.day3


import static java.lang.Integer.parseInt

class Solver {
    static int solve(List<String> input) {
        int[] counts = new int[input[0].length()]

        input.each {
            for (int i = 0; i < it.length(); i++) {
                it[i] == '1' ? counts[i]++ : counts[i]--
            }
        }

        def gammaString = ''
        def epsilonString = ''

        for (int i = 0; i < counts.size(); i++) {
            if (counts[i] > 0) {
                gammaString += '1'
                epsilonString += '0'
            } else {
                gammaString += '0'
                epsilonString += '1'
            }
        }

        parseInt(gammaString, 2) * parseInt(epsilonString, 2)
    }

    static int solve2(List<String> input) {
        def ox = re(input, 0, true)
        def co2 = re(input, 0, false)

        parseInt(ox, 2) * parseInt(co2, 2)
    }

    static String re(List<String> list, int currentBit, boolean positive) {
        if (list.size() == 1) {
            return list.first()
        } else {
            def count = count(list, currentBit)

            def newInput = filterRows(list, currentBit, count, positive)

            return re(newInput, currentBit + 1, positive)
        }
    }

    static List<String> filterRows(List<String> strings, int currentBit, int criteria, boolean positive) {
        strings.findAll {
            filterRow(criteria, positive, it[currentBit])
        }
    }

    static boolean filterRow(int criteria, boolean positive, String row) {
        if (positive) {
            if (criteria > 0) {
                row.startsWith('1')
            } else if (criteria < 0) {
                row.startsWith('0')
            } else {
                row.startsWith('1')
            }
        } else {
            if (criteria > 0) {
                row.startsWith('0')
            } else if (criteria < 0) {
                row.startsWith('1')
            } else {
                row.startsWith('0')
            }
        }
    }

    static int count(List<String> input, int bitIndex) {
        int count = 0

        input.each {
            it[bitIndex] == '1' ? count++ : count--
        }

        count
    }
}
