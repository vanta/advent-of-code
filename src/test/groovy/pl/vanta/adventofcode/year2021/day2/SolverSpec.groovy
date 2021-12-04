package pl.vanta.adventofcode.year2021.day2

import spock.lang.Specification

import static pl.vanta.adventofcode.year2021.day2.Parser.parse
import static pl.vanta.adventofcode.year2021.day2.Solver.solve
import static pl.vanta.adventofcode.year2021.day2.Solver.solve2

class SolverSpec extends Specification {
    def 'should solve example data - part 1'() {
        expect:
        solve(parse(Main.getResource("/2021/example-day2.txt").text)) == 150
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Main.getResource("/2021/day2.txt").text)) == 1604850
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Main.getResource("/2021/example-day2.txt").text)) == 900
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Main.getResource("/2021/day2.txt").text)) == -1
    }
}