package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day7.parse
import static pl.vanta.adventofcode.year2021.Day7.solve
import static pl.vanta.adventofcode.year2021.Day7.solve2

class Day7SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day7.getResource("/2021/example-day7.txt").text)) == 37
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day7.getResource("/2021/day7.txt").text)) == 335_330
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day7.getResource("/2021/example-day7.txt").text)) == 168
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day7.getResource("/2021/day7.txt").text)) == 92439766
    }

}
