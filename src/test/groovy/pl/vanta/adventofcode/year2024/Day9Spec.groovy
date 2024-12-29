package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day9Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day9()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 60, suffix: '-easy'],
                [result: 1928],
        ]
    }

    @Override
    Long getRealAnswer1() {
        6367087064415
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 2858],
        ]
    }

    @Override
    Long getRealAnswer2() {
        6390781891880
    }
}
