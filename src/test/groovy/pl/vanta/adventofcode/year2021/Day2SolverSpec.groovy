package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day2Parser.parse
import static pl.vanta.adventofcode.year2021.Day2Solver.solve
import static pl.vanta.adventofcode.year2021.Day2Solver.solve2

class Day2SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day2Solver.getResource("/2021/example-day2.txt").text)) == 150
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day2Solver.getResource("/2021/day2.txt").text)) == 1604850
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day2Solver.getResource("/2021/example-day2.txt").text)) == 900
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day2Solver.getResource("/2021/day2.txt").text)) == -1
    }
}
