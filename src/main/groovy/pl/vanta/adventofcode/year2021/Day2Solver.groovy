package pl.vanta.adventofcode.year2021

class Day2Solver {
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

    static int solve2(List<Tuple2<String, Integer>> input) {
        def position = new Position(0, 0, 0)

        input.each {
            position = reduce(position, it)
        }

        position.forward * position.depth
    }

    static Position reduce(Position p, Tuple2<String, Integer> val) {
        switch (val.first) {
            case 'forward':
                return new Position(p.forward + val.getSecond(), p.depth + (p.aim * val.getSecond()), p.aim)
            case 'up':
                return new Position(p.forward, p.depth, p.aim - val.getSecond())
            case 'down':
                return new Position(p.forward, p.depth, p.aim + val.getSecond())
        }
    }

    static class Position {
        int forward
        int depth
        int aim

        Position(final int forward, final int depth, final int aim) {
            this.forward = forward
            this.depth = depth
            this.aim = aim
        }
    }

}
