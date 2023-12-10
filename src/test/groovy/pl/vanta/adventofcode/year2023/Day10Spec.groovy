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
    List<Map> getTestData2() {
        [
                [result: 4, suffix: '_part2'],
                [result: 4, suffix: '_part2_2'],
                [result: 8, suffix: '_part2_3'],
                [result: 10, suffix: '_part2_4'],
        ]
    }

    @Override
    Integer getRealAnswer1() {
        6942
    }

    @Override
    Integer getRealAnswer2() {
        -1
    }


}
