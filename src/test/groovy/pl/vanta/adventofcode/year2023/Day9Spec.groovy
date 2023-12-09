package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day9Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day9()
    }

    @Override
    Integer getExampleAnswer1() {
        114
    }

    @Override
    Integer getRealAnswer1() {
        1868368343
    }

    @Override
    Integer getExampleAnswer2() {
        2
    }

    @Override
    Integer getRealAnswer2() {
        1022
    }
}
