package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day2Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day2()
    }

    @Override
    Integer getExampleAnswer1() {
        8
    }

    @Override
    Integer getRealAnswer1() {
        2617
    }

    @Override
    Integer getExampleAnswer2() {
        -1
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }
}
