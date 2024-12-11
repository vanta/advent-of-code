package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day11Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day11()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 55312],
        ]
    }

    @Override
    Long getRealAnswer1() {
        186424
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 3, suffix: '-part2-m1'],
                [result: 13, suffix: '-part2-m2'],
                [result: 81, suffix: '-part2-m3'],
                [result: 227, suffix: '-part2-m4'],
        ]
    }

    @Override
    Long getRealAnswer2() {
        186424
    }
}
