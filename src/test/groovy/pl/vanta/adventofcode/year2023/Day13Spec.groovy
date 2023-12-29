package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day13Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day13()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 405],
                [result: 709, suffix: '_2'],
                [result: 1, suffix: '_3'],
                [result: 2, suffix: '_4'],
                [result: 1, suffix: '_5_1'],
                [result: 8, suffix: '_5_2'],
                [result: 100, suffix: '_5_3'],
                [result: 600, suffix: '_5_4'],
                [result: 8, suffix: '_qba'],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 1],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        29846
    }

    @Override
    Long getRealAnswer2() {
        1
    }
}
