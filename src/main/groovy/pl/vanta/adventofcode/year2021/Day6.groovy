package pl.vanta.adventofcode.year2021

class Day6 {
    private static final int DAYS = 80

    static List<Integer> parse(String input) {
        input.split(',').collect { it as int }
    }

    static int solve(List<Integer> numbers) {
        def fishes = numbers.collect { new Fish(days: it) }
        println "Initial: $fishes"

        for (int i = 0; i < DAYS; i++) {
            fishes.addAll(processDay(fishes))

            println "After $i days: $fishes"
        }

        fishes.size()
    }

    private static List<Fish> processDay(List<Fish> fishes) {
        def newFish = []
        for (def fish : fishes) {
            def isNewFish = fish.dayPasses()

            if (isNewFish) {
                newFish << new Fish()
            }
        }

        newFish
    }

    static int solve2(List<Integer> numbers) {
        0
    }

    static class Fish {
        private int days

        Fish(final int days) {
            this.days = days
        }

        Fish() {
            this(8)
        }

        boolean dayPasses() {
            if(days-- == 0) {
                days = 6
                return true
            }
            
            return false
        }

        @Override
        String toString() {
            "$days"
        }
    }

}
