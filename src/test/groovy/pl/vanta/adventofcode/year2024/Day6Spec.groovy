package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day6Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day6()
    }

    @Override
    List<Map> getTestData1() {
        [
            [result: 41]
        ]
    }

    @Override
    Integer getRealAnswer1() {
        4559
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 6],
                [result: 19, suffix: '-p2-e1'],
        ]
    }

    @Override
    Integer getRealAnswer2() {
        1604
    }
}
