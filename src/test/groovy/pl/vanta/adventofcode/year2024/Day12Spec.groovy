package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day12Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day12()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 140, suffix: '-small'],
                [result: 772, suffix: '-small2'],
                [result: 1930],
        ]
    }

    @Override
    Long getRealAnswer1() {
        1304764
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 80, suffix: '-small'],
                [result: 436, suffix: '-small2'],
                [result: 236, suffix: '-small3-part2'],
                [result: 368, suffix: '-small4-part2'],
                [result: 1206],
        ]
    }

    @Override
    Long getRealAnswer2() {
        219838428124832
    }
}
