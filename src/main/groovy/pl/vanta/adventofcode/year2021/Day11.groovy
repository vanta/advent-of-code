package pl.vanta.adventofcode.year2021

class Day11 {
    static int[][] parse(String input) {
        input.split('\n')
                .collect { it.split('').collect { it as int }.toArray() }
                .toArray()
    }

    static int solve(int[][] input) {
        (1..100)
                .collect { processDay(input, it) }
                .sum()
    }

    static long solve2(int[][] input) {

        -1
    }

    static int processDay(int[][] ints, int day) {
        phase1(ints)
        int flashes = phase2(ints)
        phase3(ints)

        println("Day $day=$flashes")
        
        flashes
    }

    private static void phase1(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                ints[i][j]++
            }
        }
    }

    private static int phase2(int[][] ints) {
        def flashes = []
        def keepLooping = true

        while (keepLooping) {
            def newFlashes = []

            for (int i = 0; i < ints.length; i++) {
                for (int j = 0; j < ints.length; j++) {
                    if (ints[i][j] > 9 && !flashes.contains([i, j])) {
                        newFlashes << [i, j]
                        processAdjacent(ints, i, j)
                    }
                }
            }

            flashes.addAll(newFlashes)
            keepLooping = !newFlashes.isEmpty()
        }

        flashes.size()
    }

    private static void phase3(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                if (ints[i][j] > 9) {
                    ints[i][j] = 0
                }
            }
        }
    }

    static void processAdjacent(int[][] ints, int i, int j) {
        getAdjacent(i, j, ints.length, ints[i].length)
                .each {
                    ints[it[0]][it[1]] += 1
                }
    }

    static def getAdjacent(int i, int j, int iMax, int jMax) {
        def result = [
                [i - 1, j - 1],
                [i - 1, j],
                [i - 1, j + 1],
                [i, j - 1],
                [i, j + 1],
                [i + 1, j - 1],
                [i + 1, j],
                [i + 1, j + 1],
        ]

        result.findAll { it[0] >= 0 && it[1] >= 0 && it[0] < iMax && it[1] < jMax }
    }
}
