package pl.vanta.adventofcode.year2021

import spock.lang.Specification
import spock.lang.Unroll

import static pl.vanta.adventofcode.year2021.Day16.parse
import static pl.vanta.adventofcode.year2021.Day16.solve
import static pl.vanta.adventofcode.year2021.Day16.solve2

class Day16SolverSpec extends Specification {
    @Unroll
    def 'should solve example data - part 1 (#example)'() {
        setup:
        def parsed = parse(example)

        expect:
        solve(parsed) == expected

        where:
        example                                                    || expected
        parse(Day16.getResource('/2021/example-day16.txt').text)   || 6
        parse(Day16.getResource('/2021/example-day16-6.txt').text) || 9
        parse(Day16.getResource('/2021/example-day16-7.txt').text) || 14
        parse(Day16.getResource('/2021/example-day16-2.txt').text) || 16
        parse(Day16.getResource('/2021/example-day16-3.txt').text) || 12
        parse(Day16.getResource('/2021/example-day16-4.txt').text) || 23
        parse(Day16.getResource('/2021/example-day16-5.txt').text) || 31
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
