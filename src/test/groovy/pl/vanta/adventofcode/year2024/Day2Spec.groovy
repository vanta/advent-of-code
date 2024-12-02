package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day2Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day2()
    }

    @Override
    Integer getExampleAnswer1() {
        2
    }

    @Override
    Integer getRealAnswer1() {
        334
    }

    @Override
    Integer getExampleAnswer2() {
        4
    }

    @Override
    Integer getRealAnswer2() {
        400
    }
}
