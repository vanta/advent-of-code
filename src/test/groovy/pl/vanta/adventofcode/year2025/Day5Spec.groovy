package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day5Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day5()
    }

    @Override
    Long getRealAnswer1() {
        868
    }

    @Override
    Long getRealAnswer2() {
        354143734113772
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 3]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 14],
        ]
    }
}
