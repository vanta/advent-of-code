package pl.vanta.adventofcode.year2024


import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver
import spock.lang.Ignore

class Day11Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolver getParserSolver() {
        new Day11()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 55312],
        ]
    }

    @Override
    Long getRealAnswer1() {
        186424
    }

    @Ignore
    @Override
    List<Map> getTestData2() {
        [

        ]
    }

    @Override
    Long getRealAnswer2() {
        219838428124832
    }
}
