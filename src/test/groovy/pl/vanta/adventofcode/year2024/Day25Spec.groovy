package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolverGeneric

class Day25Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolverGeneric getParserSolver() {
        new Day25()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 3],
        ]
    }

    @Override
    Long getRealAnswer1() {
        3619
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 1],
        ]
    }

    @Override
    Long getRealAnswer2() {
        3
    }
}
