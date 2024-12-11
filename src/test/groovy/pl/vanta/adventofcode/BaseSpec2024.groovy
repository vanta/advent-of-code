package pl.vanta.adventofcode

import spock.lang.Specification

abstract class BaseSpec2024 extends Specification {
    abstract int getYear()

    abstract ParserSolver getParserSolver()

    abstract Object getRealAnswer1()

    abstract Object getRealAnswer2()

    def parserSolver = getParserSolver()

    abstract List<Map> getTestData1()

    abstract List<Map> getTestData2()

    def 'should solve example data'() {
        setup:
        def resourceName = "/${getYear()}/example-day${parserSolver.getDayNumber()}${data.suffix ?: ''}.txt"

        expect:
        parserSolver.solve(parserSolver.parse(parserSolver.getClass().getResource(resourceName).text)) == data.result

        where:
        data << getTestData1()
    }

    def 'should solve real data'() {
        expect:
        parserSolver.solveReal(parserSolver.parse(parserSolver.getClass().getResource("/${getYear()}/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer1()
    }

    def 'should solve example data - part2'() {
        setup:
        def resourceName = "/${getYear()}/example-day${parserSolver.getDayNumber()}${data.suffix ?: ''}.txt"

        expect:
        parserSolver.solve2(parserSolver.parse2(parserSolver.getClass().getResource(resourceName).text)) == data.result

        where:
        data << getTestData2()
    }

    def 'should solve real data - part2'() {
        expect:
        parserSolver.solve2Real(parserSolver.parse2(parserSolver.getClass().getResource("/${getYear()}/day${parserSolver.getDayNumber()}.txt").text)) == this.getRealAnswer2()
    }
}
