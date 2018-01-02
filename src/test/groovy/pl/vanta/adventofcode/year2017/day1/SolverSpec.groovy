package pl.vanta.adventofcode.year2017.day1

import spock.lang.Specification
import spock.lang.Unroll


class SolverSpec extends Specification {
    @Unroll
    def "should solve examples (#input -> #output)"() {
        expect:
        Solver.solve(input) == output

        where:
        input      || output
        "1122"     || 3
        "1111"     || 4
        "1234"     || 0
        "91212129" || 9
    }

    @Unroll
    def "should find groups (#input -> #output)"() {
        expect:
        Solver.findGroups(input) == output

        where:
        input           || output
        ""              || []
        "ab"            || []
        "aabb"          || ["aa", "bb"]
        "aaaa"          || ["aaaa"]
        "aaaab"         || ["aaaa"]
        "baaaab"        || ["aaaa"]
        "aaabaaabaaa"   || ["aaa", "aaa", "aaa"]
        "baaabaaabaaab" || ["aaa", "aaa", "aaa"]
    }
}
