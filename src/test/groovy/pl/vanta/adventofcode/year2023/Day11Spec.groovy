package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day11Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day11()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 374],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
        ]
    }

    @Override
    Integer getRealAnswer1() {
        -1
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }
}
