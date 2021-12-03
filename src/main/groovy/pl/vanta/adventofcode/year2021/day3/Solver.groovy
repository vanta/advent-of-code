package pl.vanta.adventofcode.year2021.day3

class Solver {
    static int solve(List<String> input) {
        int[] counts = new int[input[0].length()] 

        input.each {
            for (int i = 0; i < it.length(); i++) {
                if (it[i] == '1') {
                    counts[i]++
                } else {
                    counts[i]--
                }
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

        Integer.parseInt(gammaString, 2) * Integer.parseInt(epsilonString, 2)
    }

    static int solve2(List<String> input) {
    }


}
