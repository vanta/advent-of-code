package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day18Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day18()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 62],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: -1],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        -1
    }

    @Override
    Long getRealAnswer2() {
        -1
    }
}
