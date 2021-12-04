package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day4.parse
import static pl.vanta.adventofcode.year2021.Day4.solve
import static pl.vanta.adventofcode.year2021.Day4.solve2

class Day4SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day4.getResource("/2021/example-day4.txt").text)) == 4512
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day4.getResource("/2021/day4.txt").text)) == -1
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day4.getResource("/2021/example-day4.txt").text)) == 230
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day4.getResource("/2021/day4.txt").text)) == 3832770
    }

}
