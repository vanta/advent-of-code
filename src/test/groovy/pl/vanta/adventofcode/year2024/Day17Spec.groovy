
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
                [result: '0,1,2', suffix: '-simple1'],
                [result: '4,2,5,6,7,7,7,7,3,1,0', suffix: '-simple2'],
                [result: '0,3,5,4,3,0', suffix: '-simple3'],
        ]
    }

    @Override
    String getRealAnswer1() {
        '1,5,0,1,7,4,1,0,3'
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 117440, suffix: '-part2'],
        ]
    }

    @Override
    Long getRealAnswer2() {
        -1
    }
}
