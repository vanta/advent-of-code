package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec
import pl.vanta.adventofcode.ParserSolver

class Day7Spec extends BaseSpec {
    @Override
    ParserSolver getParserSolver() {
        new Day7()
    }

    @Override
    Long getExampleAnswer1() {
        3749
    }

    @Override
    Long getRealAnswer1() {
        1985268524462
    }

    @Override
    Long getExampleAnswer2() {
        11387
    }

    @Override
    Long getRealAnswer2() {
        150077710195188
    }
}
