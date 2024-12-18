
package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day18Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day18()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 22],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        -111
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: '117440', suffix: '-part2'],
        ]
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }
}
