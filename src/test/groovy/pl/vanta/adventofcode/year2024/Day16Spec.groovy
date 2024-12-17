
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
//                [result: 1, suffix: '-simple0'],
//                [result: 12, suffix: '-simple1'],
//                [result: 1014, suffix: '-simple2'],
//                [result: 1014, suffix: '-simple3'],
                [result: 1014, suffix: '-simple4'],
                [result: 11048, suffix: '-example2'],
                [result: 7036],
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
