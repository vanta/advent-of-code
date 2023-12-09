package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day8Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day8()
    }

    @Override
    Integer getExampleAnswer1() {
        //2
        6
    }

    @Override
    Integer getRealAnswer1() {
        16897
    }

    @Override
    Integer getExampleAnswer2() {
        6
    }

    @Override
    Long getRealAnswer2() {
        16563603485021
    }
}
