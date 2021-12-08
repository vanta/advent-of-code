package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day8.parse
import static pl.vanta.adventofcode.year2021.Day8.solve
import static pl.vanta.adventofcode.year2021.Day8.solve2

class Day8SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day8.getResource("/2021/example-day8.txt").text)) == 26
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day8.getResource("/2021/day8.txt").text)) == 387
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day8.getResource("/2021/example-day8.txt").text)) == 61_229
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day8.getResource("/2021/day8.txt").text)) == -1
    }

}
