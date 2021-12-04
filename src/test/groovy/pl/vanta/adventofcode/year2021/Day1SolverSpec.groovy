package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day1Solver.solve
import static pl.vanta.adventofcode.year2021.Day1Solver.solve2
import static pl.vanta.adventofcode.year2021.Parser.parse

class Day1SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day1Solver.getResource("/2021/example-day1.txt").text)) == 7
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day1Solver.getResource("/2021/day1.txt").text)) == 1692
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day1Solver.getResource("/2021/example-day1.txt").text)) == 5
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day1Solver.getResource("/2021/day1.txt").text)) == 1724
    }
}
