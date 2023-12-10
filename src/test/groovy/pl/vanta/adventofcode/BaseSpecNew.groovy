package pl.vanta.adventofcode

import spock.lang.Specification

abstract class BaseSpecNew extends Specification {
    abstract ParserSolver getParserSolver()
    abstract Object getRealAnswer1()
    abstract Object getRealAnswer2()

    def parserSolver = getParserSolver()

    abstract List<Map> getTestData1()
    abstract List<Map> getTestData2()

    def 'should solve example data'() {
        setup:
        def resourceName = "/2023/example-day${parserSolver.getDayNumber()}${data.suffix ?: ''}.txt"

        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource(resourceName).text)) == data.result

        where:
        data << getTestData1()
    }

    def 'should solve real data'() {
        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource("/2023/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer1()
    }

    def 'should solve example data - part2'() {
        setup:
        def resourceName = "/2023/example-day${parserSolver.getDayNumber()}${data.suffix ?: ''}.txt"

        expect:
        parserSolver.solve2(parserSolver.parse(parserSolver.getClass().getResource(resourceName).text)) == data.result

        where:
        data << getTestData2()
    }

    def 'should solve real data - part2'() {
        expect:
        parserSolver.solve2(parserSolver.parse(parserSolver.getClass().getResource("/2023/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer2()
    }
}
