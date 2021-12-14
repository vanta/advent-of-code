package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day14.parse
import static pl.vanta.adventofcode.year2021.Day14.solve
import static pl.vanta.adventofcode.year2021.Day14.solve2

class Day14SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day14.getResource("/2021/example-day14.txt").text)) == 1588
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day14.getResource("/2021/day14.txt").text)) == 1
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day14.getResource("/2021/example-day14.txt").text)) == 1
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day14.getResource("/2021/day14.txt").text)) == 1
    }
}
