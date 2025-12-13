package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day11Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day11()
    }

    @Override
    Long getRealAnswer1() {
        0
    }

    @Override
    Long getRealAnswer2() {
        0
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 2]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 2, suffix: '-part2'],
        ]
    }
}
