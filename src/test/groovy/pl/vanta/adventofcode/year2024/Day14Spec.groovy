package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day14Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day14()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 12],
        ]
    }

    @Override
    Long getRealAnswer1() {
        233709840
    }

    @Override
    List<Map> getTestData2() {
        [
                [],
        ]
    }

    @Override
    Long getRealAnswer2() {
        6620
    }

    @Override
    boolean skipExamplePart2() {
        true
    }
}
