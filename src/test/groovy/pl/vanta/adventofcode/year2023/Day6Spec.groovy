package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day6Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day6()
    }

    @Override
    Integer getExampleAnswer1() {
        288
    }

    @Override
    Integer getRealAnswer1() {
        505494
    }

    @Override
    Integer getExampleAnswer2() {
        71503
    }

    @Override
    Integer getRealAnswer2() {
        23632299
    }
}
