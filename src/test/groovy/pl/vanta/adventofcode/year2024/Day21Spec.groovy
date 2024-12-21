

package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day21Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day21()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 126384],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        1
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 1],
        ]
    }

    @Override
    Integer getRealAnswer2() {
        1
    }
}
