package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day5Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day5()
    }

    @Override
    Integer getExampleAnswer1() {
        143
    }

    @Override
    Integer getRealAnswer1() {
        4957
    }

    @Override
    Integer getExampleAnswer2() {
        9
    }

    @Override
    Integer getRealAnswer2() {
        1933
    }
}
