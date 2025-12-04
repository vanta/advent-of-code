package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day1Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day1()
    }

    @Override
    Integer getRealAnswer1() {
        989
    }

    @Override
    Integer getRealAnswer2() {
        5941
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
                [result: 6],
                [result: 5, suffix: '-examples'],
        ]
    }
}
