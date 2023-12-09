package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day7Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day7()
    }

    @Override
    Integer getExampleAnswer1() {
        6440
    }

    @Override
    Integer getRealAnswer1() {
        253205868
    }

    @Override
    Integer getExampleAnswer2() {
        1
    }

    @Override
    Integer getRealAnswer2() {
        1
    }
}
