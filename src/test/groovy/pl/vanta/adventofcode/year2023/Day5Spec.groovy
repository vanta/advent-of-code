package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day5Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day5()
    }

    @Override
    Integer getExampleAnswer1() {
        35
    }

    @Override
    Integer getRealAnswer1() {
        662197086
    }

    @Override
    Integer getExampleAnswer2() {
        46
    }

    @Override
    Integer getRealAnswer2() {
        52510809
    }
}
