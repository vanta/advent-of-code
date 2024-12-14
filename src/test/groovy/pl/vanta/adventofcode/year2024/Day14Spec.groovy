package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver
import spock.lang.Ignore

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

    @Ignore
    @Override
    List<Map> getTestData2() {
        [
                [result: 875318608908],
        ]
    }

    @Override
    Long getRealAnswer2() {
        93866170395343
    }
}
