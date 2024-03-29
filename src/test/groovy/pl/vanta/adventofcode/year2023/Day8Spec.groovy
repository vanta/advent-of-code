package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day8Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day8()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 2],
                [result: 6, suffix: '_2']
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 6, suffix: '_part2']
        ]
    }

    @Override
    Integer getRealAnswer1() {
        16897
    }

    @Override
    Long getRealAnswer2() {
        16563603485021
    }

}
