package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day3Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day3()
    }

    @Override
    Integer getExampleAnswer1() {
        4361
    }

    @Override
    Integer getRealAnswer1() {
        498559
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
