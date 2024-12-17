
package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day17Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day17()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: '4,6,3,5,6,3,5,2,1,0'],
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
