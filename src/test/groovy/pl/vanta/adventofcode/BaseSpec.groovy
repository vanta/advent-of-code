package pl.vanta.adventofcode

import spock.lang.Specification

abstract class BaseSpec extends Specification {
    abstract ParserSolver getParserSolver()

    def parserSolver = getParserSolver()

    def 'should solve example data'() {
        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource("/2022/example-day${parserSolver.getDayNumber()}.txt").text)) == parserSolver.getExampleAnswer1()
    }

    def 'should solve real data'() {
        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource("/2022/day${parserSolver.getDayNumber()}.txt").text)) == parserSolver.getRealAnswer1()
    }

    def 'should solve example data - part2'() {
        expect:
        parserSolver.solve2(parserSolver.parse(parserSolver.getClass().getResource("/2022/example-day${parserSolver.getDayNumber()}.txt").text)) == parserSolver.getExampleAnswer2()
    }

    def 'should solve real data - part2'() {
        expect:
        parserSolver.solve2(parserSolver.parse(parserSolver.getClass().getResource("/2022/day${parserSolver.getDayNumber()}.txt").text)) == parserSolver.getRealAnswer2()
    }
}
