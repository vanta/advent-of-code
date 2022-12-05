package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.ParserSolver

import static pl.vanta.adventofcode.year2022.Day2.Result.DRAW
import static pl.vanta.adventofcode.year2022.Day2.Result.LOSE
import static pl.vanta.adventofcode.year2022.Day2.Result.WIN
import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.PAPER
import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.ROCK
import static pl.vanta.adventofcode.year2022.Day2.RockPaperScissors.SCISSORS

class Day2 implements ParserSolver<List<Tuple2<String, String>>, Integer> {
    @Override
    int getDayNumber() {
        2
    }

    @Override
    List<Tuple2<String, String>> parse(final String lines) {
        lines.split('\n')
                .collect { it.split(' ') }
                .collect { new Tuple2<>(it[0], it[1]) }
    }

    @Override
    Integer solve(final List<Tuple2<String, String>> parsedInput) {
        parsedInput
                .collect { new Tuple2<>(map(it.first), map(it.second)) }
                .collect { it.getSecond().points(it.getFirst()) }
                .sum() as Integer
    }

    @Override
    Integer solve2(final List<Tuple2<String, String>> parsedInput) {
        parsedInput
                .collect { new Tuple2<RockPaperScissors, Result>(map(it.first), mapResult(it.second)) }
                .collect { new Tuple2<>(it.first, figureOut(it.first, it.second)) }
                .collect { it.getSecond().points(it.getFirst()) }
                .sum() as Integer
    }

    static RockPaperScissors map(String s) {
        switch (s) {
            case 'A':
            case 'X': return ROCK
            case 'B':
            case 'Y': return PAPER
            case 'C':
            case 'Z': return SCISSORS
        }
    }

    RockPaperScissors figureOut(RockPaperScissors opponent, Result expectedResult) {
        if (expectedResult == DRAW) {
            opponent
        } else if (expectedResult == WIN) {
            RockPaperScissors.values()[opponent.loseTo]
        } else {
            RockPaperScissors.values()[opponent.winsOver]
        }
    }

    enum Result {
        LOSE, DRAW, WIN
    }

    static Result mapResult(String s) {
        switch (s) {
            case 'X': return LOSE
            case 'Y': return DRAW
            case 'Z': return WIN
        }
    }

    enum RockPaperScissors {
        ROCK(2, 1), PAPER(0, 2), SCISSORS(1, 0)

        private final int winsOver
        private final int loseTo

        RockPaperScissors(final int winsOver, int loseTo) {
            this.winsOver = winsOver
            this.loseTo = loseTo
        }

        int points(RockPaperScissors rps) {
            def initialPoints = this.ordinal() + 1

            initialPoints + resultPoints(rps)
        }

        Result result(RockPaperScissors opponent) {
            if (this == opponent) {
                return DRAW
            } else if (this.winsOver == opponent.ordinal()) {
                return WIN
            } else {
                return LOSE
            }
        }

        private int resultPoints(RockPaperScissors rps) {
            switch (this.result(rps)) {
                case WIN: return 6
                case DRAW: return 3
                case LOSE: return 0
            }
        }

    }
}
