package pl.vanta.adventofcode.year2021

class Day6 {
    private static final int DAYS = 80
    private static final int DAYS2 = 256

    static List<Integer> parse(String input) {
        input.split(',').collect { it as int }
    }

    static int solve(List<Integer> numbers) {
        internalSolve(numbers, DAYS)
    }

    static long solve2(List<Integer> numbers) {
        internalSolve(numbers, DAYS2)
    }

    private static long internalSolve(List<Integer> numbers, int days) {
        def fishes = new Fishes(numbers)
        println "Initial: $fishes"

        for (int i = 0; i < days; i++) {
            fishes.dayPasses()

            println "After $i days: $fishes"
        }

        fishes.count()
    }

    static class Fishes {
        long[] shoal = new long[9]

        Fishes(List<Integer> fish) {
            fish.every {
                shoal[it] += 1
            }
        }

        void dayPasses() {
            long[] newShoal = new long[9]

            newShoal[0] = shoal[1]
            newShoal[1] = shoal[2]
            newShoal[2] = shoal[3]
            newShoal[3] = shoal[4]
            newShoal[4] = shoal[5]
            newShoal[5] = shoal[6]
            newShoal[6] = shoal[7] + shoal[0]
            newShoal[7] = shoal[8]
            newShoal[8] = shoal[0]

            shoal = newShoal
        }

        long count() {
            shoal.inject { a, b -> a + b }
        }

        @Override
        String toString() {
            "Fishes: $shoal"
        }
    }

}
