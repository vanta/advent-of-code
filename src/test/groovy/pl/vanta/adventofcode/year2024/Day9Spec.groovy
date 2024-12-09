package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day9Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day9()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 1928],
//                [result: 2, suffix: '-easy1'],
//                [result: 4, suffix: '-easy2'],
        ]
    }

    @Override
    Long getRealAnswer1() {
        394
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 34],
                [result: 9, suffix: '-p2-easy1']
        ]
    }

    @Override
    Long getRealAnswer2() {
        1277
    }
}
