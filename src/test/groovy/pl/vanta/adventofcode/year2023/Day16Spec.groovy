package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day16Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day16()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 46],
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
        8901
    }

    @Override
    Long getRealAnswer2() {
        231844
    }
}
