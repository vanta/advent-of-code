package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day19Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day19()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 19114],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
//                [result: 952408144115],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        1
    }

    @Override
    Long getRealAnswer2() {
        1
    }
}
