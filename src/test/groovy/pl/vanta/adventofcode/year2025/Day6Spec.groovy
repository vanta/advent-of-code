package pl.vanta.adventofcode.year2025

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day6Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        return 2025
    }

    @Override
    ParserSolver getParserSolver() {
        new Day6()
    }

    @Override
    Long getRealAnswer1() {
        4309240495780
    }

    @Override
    Long getRealAnswer2() {
        9170286552289
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 4277556]
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 3263827],
        ]
    }
}
