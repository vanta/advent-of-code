package pl.vanta.adventofcode.year2021

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.Day10.parse
import static pl.vanta.adventofcode.year2021.Day10.solve
import static pl.vanta.adventofcode.year2021.Day10.solve2

class Day10SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day10.getResource("/2021/example-day10.txt").text)) == 26_397
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day10.getResource("/2021/day10.txt").text)) == 243_939
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day10.getResource("/2021/example-day10.txt").text)) == 288_957
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day10.getResource("/2021/day10.txt").text)) == 2_421_222_841
    }

}
