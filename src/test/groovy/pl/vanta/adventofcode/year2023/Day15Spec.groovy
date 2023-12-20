package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day15Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day15()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 1320],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 145],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        516804
    }

    @Override
    Long getRealAnswer2() {
        231844
    }
}
