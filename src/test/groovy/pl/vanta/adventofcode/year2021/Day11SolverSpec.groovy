package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day11.parse
import static pl.vanta.adventofcode.year2021.Day11.solve
import static pl.vanta.adventofcode.year2021.Day11.solve2

class Day11SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day11.getResource("/2021/example-day11.txt").text)) == 1656
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day11.getResource("/2021/day11.txt").text)) == 1652
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day11.getResource("/2021/example-day11.txt").text)) == 195
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day11.getResource("/2021/day11.txt").text)) == 220
    }

}
