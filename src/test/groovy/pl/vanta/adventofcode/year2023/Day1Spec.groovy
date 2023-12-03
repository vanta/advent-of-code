package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day1Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day1()
    }

    @Override
    Integer getExampleAnswer1() {
        142
    }

    @Override
    Integer getRealAnswer1() {
        54081
    }

    @Override
    Integer getExampleAnswer2() {
        45000
    }

    @Override
    Integer getRealAnswer2() {
        200116
    }
}
