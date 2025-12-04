package pl.vanta.adventofcode.year2023

import pl.vanta.adventofcode.BaseSpec2023
import pl.vanta.adventofcode.ParserSolver

class Day18Spec extends BaseSpec2023 {
    @Override
    ParserSolver getParserSolver() {
        new Day18()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 62],
        ]
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 952408144115],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        95356
    }

    @Override
    Long getRealAnswer2() {
        92291468914147
    }
}
