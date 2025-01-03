package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver

class Day7Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day7()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 3749]
        ]
    }

    @Override
    Long getRealAnswer1() {
        1985268524462
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 11387]
        ]
    }

    @Override
    Long getRealAnswer2() {
        150077710195188
    }
}
