package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day17Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day17()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 102],
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
