package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day3Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day3()
    }

    @Override
    Object getExampleAnswer1() {
        157
    }

    @Override
    Object getRealAnswer1() {
        7742
    }

    @Override
    Object getExampleAnswer2() {
        -1
    }

    @Override
    Object getRealAnswer2() {
        -1
    }
}
