
package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day20Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day20()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 32000000],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
//                [result: 167409079868000],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        -1
    }

    @Override
    Long getRealAnswer2() {
        -1
    }
}
