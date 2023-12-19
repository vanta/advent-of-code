package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day14Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day14()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 136],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 64],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        109939
    }

    @Override
    Long getRealAnswer2() {
        1
    }
}
