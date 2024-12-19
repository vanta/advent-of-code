
package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day19Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day19()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 6],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        -111
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: '117440'],
        ]
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }
}
