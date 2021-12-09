package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day9.parse
import static pl.vanta.adventofcode.year2021.Day9.solve
import static pl.vanta.adventofcode.year2021.Day9.solve2

class Day9SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day9.getResource("/2021/example-day9.txt").text)) == 15
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day9.getResource("/2021/day9.txt").text)) == 585
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day9.getResource("/2021/example-day9.txt").text)) == 1_134
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day9.getResource("/2021/day9.txt").text)) == 827_904
    }

}
