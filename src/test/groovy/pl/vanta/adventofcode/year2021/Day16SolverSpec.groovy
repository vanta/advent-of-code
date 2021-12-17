package pl.vanta.adventofcode.year2021

import spock.lang.Specification
import spock.lang.Unroll

import static pl.vanta.adventofcode.year2021.Day16.parse
import static pl.vanta.adventofcode.year2021.Day16.solve
import static pl.vanta.adventofcode.year2021.Day16.solve2

class Day16SolverSpec extends Specification {
    @Unroll
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Day16.getResource("/2021/$example").text)) == expected

        where:
        example               || expected
        'example-day16.txt'   || 6
        'example-day16-2.txt' || 16
        'example-day16-3.txt' || 12
        'example-day16-4.txt' || 23
        'example-day16-5.txt' || 31
        'example-day16-6.txt' || 9
        'example-day16-7.txt' || 14

    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day16.getResource("/2021/day16.txt").text)) == 583
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day16.getResource("/2021/example-day16.txt").text)) == 315
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day16.getResource("/2021/day16.txt").text)) == 2927
    }
}
