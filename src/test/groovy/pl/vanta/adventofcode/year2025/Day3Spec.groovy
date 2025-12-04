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
        16927
    }

    @Override
    Long getRealAnswer2() {
        167384358365132
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 357]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 3121910778619],
        ]
    }
}
