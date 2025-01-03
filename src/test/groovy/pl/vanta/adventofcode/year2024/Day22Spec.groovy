

package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day22Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day22()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 37327623],
        ]
    }

    @Override
    Long getRealAnswer1() {
        19877757850
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 23, suffix: '-part2'],
        ]
    }

    @Override
    Long getRealAnswer2() {
        2399
    }
}
