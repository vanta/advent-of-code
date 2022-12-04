package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.ParserSolver

import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.PAPER
import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.ROCK
import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.SCISSORS

class Day2 implements ParserSolver<List<Tuple2<RockPaperScissors, RockPaperScissors>>, Integer> {
    @Override
    int getDayNumber() {
        2
    }

    @Override
    List<Tuple2<RockPaperScissors, RockPaperScissors>> parse(final String lines) {
        lines.split('\n')
                .collect { it.split(' ') }
                .collect { new Tuple2<>(map(it[0]), map(it[1])) }
    }

    @Override
    Integer solve(final List<Tuple2<RockPaperScissors, RockPaperScissors>> parsedInput) {
        parsedInput
                .collect {it.getSecond().points(it.getFirst()) }
                .sum()
    }

    @Override
    Integer solve2(final List<Tuple2<RockPaperScissors, RockPaperScissors>> parsedInput) {

    }

    RockPaperScissors map(String s) {
        switch (s) {
            case 'A':
            case 'X': return ROCK
            case 'B':
            case 'Y': return PAPER
            case 'C':
            case 'Z': return SCISSORS
        }
    }

    enum RockPaperScissors {
        ROCK, PAPER, SCISSORS

        int points(RockPaperScissors rps) {
            def initialPoints = this.ordinal() + 1

            initialPoints + result(rps)
        }

        private int result(RockPaperScissors rps) {
            if (this == rps) {
                3
            } else if (this == ROCK && rps == SCISSORS || this == PAPER && rps == ROCK || this == SCISSORS && rps == PAPER) {
                6
            } else {
                0
            }
        }
    }
}
