package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day5.parse
import static pl.vanta.adventofcode.year2021.Day5.solve
import static pl.vanta.adventofcode.year2021.Day5.solve2

class Day5SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day5.getResource("/2021/example-day5.txt").text)) == 5
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day5.getResource("/2021/day5.txt").text)) == 7297
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day5.getResource("/2021/example-day5.txt").text)) == 12
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day5.getResource("/2021/day5.txt").text)) == 21038
    }

}
