package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day1Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day1()
    }

    @Override
    Integer getExampleAnswer1() {
        11
    }

    @Override
    Integer getRealAnswer1() {
        1223326
    }

    @Override
    Integer getExampleAnswer2() {
        31
    }

    @Override
    Integer getRealAnswer2() {
        21070419
    }
}
