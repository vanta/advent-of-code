package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day15.parse
import static pl.vanta.adventofcode.year2021.Day15.solve
import static pl.vanta.adventofcode.year2021.Day15.solve2

class Day15SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day15.getResource("/2021/example-day15.txt").text)) == 40
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day15.getResource("/2021/day15.txt").text)) == 583
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day15.getResource("/2021/example-day15.txt").text)) == 315
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day15.getResource("/2021/day15.txt").text)) == 2927
    }
}
