package pl.vanta.adventofcode.year2017.day2

import spock.lang.Specification
import spock.lang.Unroll

class Solver2Spec extends Specification {
    @Unroll
    def "should solve examples (#input -> #output)"() {
        expect:
        Solver2.solve(input) == output

        where:
        input  || output
        """5 1 9 5
7 5 3
2 4 6 8
""" || 18
    }

    @Unroll
    def "should find diff (#input -> #output)"() {
        expect:
        Solver2.findDiff(input) == output

        where:
        input           || output
        [2, 2]          || 0
        [2, 2, 2, 2, 2] || 0
        [1, 2]          || 1
        [2, 1, 2, 1]    || 1
        [1, 2, 3, 4, 5] || 4
        [2, 4, 5, 1, 3] || 4
    }
}
