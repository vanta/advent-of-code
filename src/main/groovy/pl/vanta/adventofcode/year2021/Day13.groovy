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
                .collect { new Tuple2<>(it[0] as int, it[1] as int) }

        new Input(dots: dots, folds: folds)
    }

    static int solve(Input input) {

    }

    static long solve2(Input input) {
    }

    static class Input {
        List<Tuple2<Integer, Integer>> dots = []
        List<Fold> folds = []
    }

    static class Fold {
        char orientation
        int value
    }
}
