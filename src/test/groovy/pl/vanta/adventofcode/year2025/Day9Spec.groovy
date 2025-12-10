package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day9Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day9()
    }

    @Override
    Long getRealAnswer1() {
        4777967538
    }

    @Override
    Long getRealAnswer2() {
        -1
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 50]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 24],
                [result: 40, suffix: '-p2'],
                [result: 35, suffix: '-p3'],
                [result: 66, suffix: '-p4'],
                [result: 8, suffix: '-p5'],
        ]
    }
}
