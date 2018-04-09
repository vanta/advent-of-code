package pl.vanta.adventofcode.year2017.day3

import spock.lang.Specification
import spock.lang.Unroll

class Solver3Spec extends Specification {
    @Unroll
    def "should solve examples (#input -> #output)"() {
        expect:
        Solver3.solve(input as String) == output

        where:
        input || output
        1     || 0
        2     || 1
        3     || 2
        4     || 1
        5     || 2
        6     || 1
        7     || 2
        8     || 1
        9     || 2
        10    || 3
        11    || 2
        12    || 3
        23    || 2
        1024  || 31
    }

}
