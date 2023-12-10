package pl.vanta.adventofcode

import spock.lang.Specification

abstract class BaseSpec extends Specification {
    abstract ParserSolver getParserSolver()
    abstract Object getExampleAnswer1()
    abstract Object getRealAnswer1()
    abstract Object getExampleAnswer2()
    abstract Object getRealAnswer2()

    def parserSolver = getParserSolver()

    def 'should solve example data'() {
        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource("/2023/example-day${parserSolver.getDayNumber()}.txt").text)) == this.getExampleAnswer1()
    }

    def 'should solve real data'() {
        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource("/2023/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer1()
    }

    def 'should solve example data - part2'() {
        setup:
        def resource = parserSolver.getClass().getResource("/2023/example-day${parserSolver.getDayNumber()}-part-two.txt")

        if(!resource) {
            resource = parserSolver.getClass().getResource("/2023/example-day${parserSolver.getDayNumber()}.txt")
        }

        expect:
        parserSolver.solve2(parserSolver.parse(resource.text)) == this.getExampleAnswer2()
    }

    def 'should solve real data - part2'() {
        expect:
        parserSolver.solve2(parserSolver.parse(parserSolver.getClass().getResource("/2023/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer2()
    }
}
