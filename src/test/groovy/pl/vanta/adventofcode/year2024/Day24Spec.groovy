package pl.vanta.adventofcode.year2024

import pl.vanta.adventofcode.BaseSpec2024
import pl.vanta.adventofcode.ParserSolverGeneric

class Day24Spec extends BaseSpec2024 {
    @Override
    int getYear() {
        2024
    }

    @Override
    ParserSolverGeneric getParserSolver() {
        new Day24()
    }

    @Override
    List<Map> getTestData1() {
        [
                [result: 4],
                [result: 2024, suffix: '-larger'],
        ]
    }

    @Override
    Long getRealAnswer1() {
        48063513640678
    }

    @Override
    List<Map> getTestData2() {
        [
                [result: 'z00,z01,z02,z05', suffix: '-part2'],
        ]
    }

    @Override
    String getRealAnswer2() {
        'aaaa'
    }
}
