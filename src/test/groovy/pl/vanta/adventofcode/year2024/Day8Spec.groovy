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
                [result: 14]
        ]
    }

    @Override
    Long getRealAnswer1() {
        1
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
