package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day13.parse
import static pl.vanta.adventofcode.year2021.Day13.solve
import static pl.vanta.adventofcode.year2021.Day13.solve2

class Day13SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day13.getResource("/2021/example-day13.txt").text)) == 17
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day13.getResource("/2021/day13.txt").text)) == 818
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day13.getResource("/2021/example-day13.txt").text)) == -1
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day13.getResource("/2021/day13.txt").text)) == -1
    }
}
