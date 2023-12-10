package pl.vanta.adventofcode.year2023


import pl.vanta.adventofcode.BaseSpecNew
import pl.vanta.adventofcode.ParserSolver

class Day10Spec extends BaseSpecNew {
    @Override
    ParserSolver getParserSolver() {
        new Day10()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 4],
                [result: 8, suffix: '_2']
        ]
    }

    @Override
    List<List> getTestData2() {
        []
    }

    @Override
    Integer getRealAnswer1() {
        -1
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }


}
