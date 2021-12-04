package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day3Parser.parse
import static pl.vanta.adventofcode.year2021.Day3Solver.solve
import static pl.vanta.adventofcode.year2021.Day3Solver.solve2

class Day3SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day3Solver.getResource("/2021/example-day3.txt").text)) == 198
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day3Solver.getResource("/2021/day3.txt").text)) == 4118544
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day3Solver.getResource("/2021/example-day3.txt").text)) == 230
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day3Solver.getResource("/2021/day3.txt").text)) == 3832770
    }

}
