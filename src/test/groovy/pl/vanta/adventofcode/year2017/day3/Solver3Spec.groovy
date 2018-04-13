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
        13    || 4
        14    || 3
        15    || 2
        16    || 3
        17    || 4
        18    || 3
        19    || 2
        20    || 3
        21    || 4
        22    || 3
        23    || 2
        24    || 3
        25    || 4
        26    || 5
        27    || 4
        28    || 3
        29    || 4
        30    || 5
        31    || 6
        32    || 5
        33    || 4
        34    || 3
        35    || 4
        36    || 5
        37    || 6
        38    || 5
        39    || 4
        40    || 3
        41    || 4
        42    || 5
        43    || 6
        44    || 5
        45    || 4
        46    || 3
        47    || 4
        48    || 5
        49    || 6
        1024  || 31
    }

}
