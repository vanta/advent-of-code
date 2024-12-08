package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day8Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day8()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 14],
                [result: 2, suffix: '-easy1'],
                [result: 4, suffix: '-easy2'],
        ]
    }

    @Override
    Long getRealAnswer1() {
        394
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 1]
        ]
    }

    @Override
    Long getRealAnswer2() {
        1
    }
}
