
package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day16Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day16()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 7036],
                [result: 11048, suffix: '-example2'],
        ]
    }

    @Override
    Long getRealAnswer1() {
        1559280
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 9021],
        ]
    }

    @Override
    Long getRealAnswer2() {
        6620
    }
}
