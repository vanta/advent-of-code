package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day2Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day2()
    }

    @Override
    Long getRealAnswer1() {
        -1
    }

    @Override
    Long getRealAnswer2() {
        -1
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 1227775554L]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: -1],
        ]
    }
}
