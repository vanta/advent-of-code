package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day6.parse
import static pl.vanta.adventofcode.year2021.Day6.solve
import static pl.vanta.adventofcode.year2021.Day6.solve2

class Day6SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day6.getResource("/2021/example-day6.txt").text)) == 5934
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day6.getResource("/2021/day6.txt").text)) == 352872
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day6.getResource("/2021/example-day6.txt").text)) == 12
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day6.getResource("/2021/day6.txt").text)) == 21038
    }

}
