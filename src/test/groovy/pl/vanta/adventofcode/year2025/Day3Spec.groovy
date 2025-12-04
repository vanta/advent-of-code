package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day3Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day3()
    }

    @Override
    Long getRealAnswer1() {
        11
    }

    @Override
    Long getRealAnswer2() {
        11
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 11]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 11],
        ]
    }
}
