package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day7Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day7()
    }

    @Override
    Integer getExampleAnswer1() {
        3749
    }

    @Override
    Integer getRealAnswer1() {
        4559
    }

    @Override
    Integer getExampleAnswer2() {
        6
    }

    @Override
    Integer getRealAnswer2() {
        0
    }
}
