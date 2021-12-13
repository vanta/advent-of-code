package pl.vanta.adventofcode.year2021

class Day13 {
    static Input parse(String input) {
        def folds = input.split('\n')
                .findAll { it.startsWith('fold') }
                .collect { it.replace('fold along ', '') }
                .collect { it.split('=') }
                .collect { new Fold(orientation: it[0].chars[0], value: it[1] as int) }

        def dots = input.split('\n')
                .findAll { it.contains(',') }
                .collect { it.split(',') }
                .collect { new Tuple2<>(it[0] as int, it[1] as int) } as Set

        new Input(paper: new Paper(dots), folds: folds)
    }

    static int solve(Input input) {
        def fold = input.folds[0]

        input.paper.fold(fold.getOrientation(), fold.getValue())

        input.paper.countDots()
    }

    static long solve2(Input input) {

        0
    }

    static class Input {
        Paper paper
        List<Fold> folds = []
    }

    static class Fold {
        char orientation
        int value
    }

    static class Paper {
        private static final X = 'x' as char
        private static final Y = 'y' as char
        Set<Tuple2<Integer, Integer>> dots
        int maxX
        int maxY

        Paper(Set<Tuple2<Integer, Integer>> dots) {
            this.dots = dots

            maxX = dots.collect { it.getFirst() }.max()
            maxY = dots.collect { it.getSecond() }.max()
        }

        int countDots() {
            dots.size()
        }

        void fold(char orientation, int value) {
            def dotsToRemove
            def dotsToAdd

            if (X == orientation) {
                dotsToRemove = dots.findAll { it.getFirst() > value }
                dotsToAdd = dotsToRemove.collect { new Tuple2<>(maxX - it.getFirst(), it.getSecond()) }
            } else {
                dotsToRemove = dots.findAll { it.getSecond() > value }
                dotsToAdd = dotsToRemove.collect { new Tuple2<>(it.getFirst(), maxY - it.getSecond()) }
            }

            dots.removeAll(dotsToRemove)
            dots.addAll(dotsToAdd)

            println("Dots=$dots")
        }
    }
}
