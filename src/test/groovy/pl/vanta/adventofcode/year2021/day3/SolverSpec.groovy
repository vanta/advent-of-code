package pl.vanta.adventofcode.year2021.day3

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.day3.Parser.parse
import static pl.vanta.adventofcode.year2021.day3.Solver.solve
import static pl.vanta.adventofcode.year2021.day3.Solver.solve2

class SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Main.getResource("/2021/example-day3.txt").text)) == 198
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Main.getResource("/2021/day3.txt").text)) == 4118544
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Main.getResource("/2021/example-day3.txt").text)) == 900
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Main.getResource("/2021/day3.txt").text)) == -1
    }

}
