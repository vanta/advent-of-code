package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day4Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day4()
    }

    @Override
    Long getRealAnswer1() {
        1416
    }

    @Override
    Long getRealAnswer2() {
        9086
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 13]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 43],
        ]
    }
}
