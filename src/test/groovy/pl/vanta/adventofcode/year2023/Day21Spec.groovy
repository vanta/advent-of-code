
package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec2023
import pl.vanta.adventofcode.ParserSolver

class Day21Spec extends BaseSpec2023 {
    @Override
    ParserSolver getParserSolver() {
        new Day21()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 16],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 16],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        3689
    }

    @Override
    Long getRealAnswer2() {
        610158187362102
    }
}
