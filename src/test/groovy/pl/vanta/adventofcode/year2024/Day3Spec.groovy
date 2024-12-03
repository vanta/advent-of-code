package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day3Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day3()
    }

    @Override
    Integer getExampleAnswer1() {
        161
    }

    @Override
    Integer getRealAnswer1() {
        174103751
    }

    @Override
    Integer getExampleAnswer2() {
        48
    }

    @Override
    Integer getRealAnswer2() {
        100411201
    }
}
