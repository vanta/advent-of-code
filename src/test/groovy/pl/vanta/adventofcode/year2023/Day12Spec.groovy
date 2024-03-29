package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day12Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day12()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 21],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 525152],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        7032
    }

    @Override
    Long getRealAnswer2() {
        1493340882140
    }
}
