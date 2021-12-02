package pl.vanta.adventofcode.year2021.day2

class Solver {
    static int solve(List<Tuple2<String, Integer>> input) {
        def forward = input
                .findAll { it.first == 'forward' }
                .sum { it.getSecond() }

        def up = input
                .findAll { it.first == 'up' }
                .sum { it.getSecond() }

        def down = input
                .findAll { it.first == 'down' }
                .sum { it.getSecond() }
                
        forward * (down - up)
    }
}
