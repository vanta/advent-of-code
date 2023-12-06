package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day4Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day4()
    }

    @Override
    Integer getExampleAnswer1() {
        13
    }

    @Override
    Integer getRealAnswer1() {
        17803
    }

    @Override
    Integer getExampleAnswer2() {
        30
    }

    @Override
    Integer getRealAnswer2() {
        5554894
    }
}
