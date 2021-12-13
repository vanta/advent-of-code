package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day12.parse
import static pl.vanta.adventofcode.year2021.Day12.solve
import static pl.vanta.adventofcode.year2021.Day12.solve2

class Day12SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day12.getResource("/2021/example-day12.txt").text)) == 10
        solve(parse(Day12.getResource("/2021/example-day12-2.txt").text)) == 19
        solve(parse(Day12.getResource("/2021/example-day12-3.txt").text)) == 226
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day12.getResource("/2021/day12.txt").text)) == 3421
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day12.getResource("/2021/example-day12.txt").text)) == 36
        solve2(parse(Day12.getResource("/2021/example-day12-2.txt").text)) == 103
        solve2(parse(Day12.getResource("/2021/example-day12-3.txt").text)) == 3509
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day12.getResource("/2021/day12.txt").text)) == 220
    }
}
