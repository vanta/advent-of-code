package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day2Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day2()
    }

    @Override
    Object getExampleAnswer1() {
        15
    }

    @Override
    Object getRealAnswer1() {
        13221
    }

    @Override
    Object getExampleAnswer2() {
        return null
    }

    @Override
    Object getRealAnswer2() {
        return null
    }
}
