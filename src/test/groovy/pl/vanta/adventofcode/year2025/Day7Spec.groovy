package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day7Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day7()
    }

    @Override
    Long getRealAnswer1() {
        1660
    }

    @Override
    Long getRealAnswer2() {
        305999729392659
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 21]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 40],
        ]
    }
}
