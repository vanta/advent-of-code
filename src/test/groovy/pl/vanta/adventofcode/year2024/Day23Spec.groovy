package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolver
import pl.vanta.adventofcode.ParserSolverGeneric

class Day23Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolverGeneric getParserSolver() {
        new Day23()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 7],
        ]
    }

    @Override
    Long getRealAnswer1() {
        1348
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 'co,de,ka,ta'],
        ]
    }

    @Override
    String getRealAnswer2() {
        'am,bv,ea,gh,is,iy,ml,nj,nl,no,om,tj,yv'
    }
}
