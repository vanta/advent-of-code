package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day10Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day10()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 1, suffix: '-small'],
                [result: 2, suffix: '-small2'],
                [result: 4, suffix: '-small3'],
                [result: 3, suffix: '-small4'],
                [result: 36],
        ]
    }

    @Override
    Long getRealAnswer1() {
        652
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 2858],
        ]
    }

    @Override
    Long getRealAnswer2() {
        1277
    }
}
