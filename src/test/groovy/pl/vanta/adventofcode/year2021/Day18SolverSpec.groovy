package pl.vanta.adventofcode.year2021

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

import static pl.vanta.adventofcode.year2021.Day18.parse
import static pl.vanta.adventofcode.year2021.Day18.solve
import static pl.vanta.adventofcode.year2021.Day18.solve2
import static pl.vanta.adventofcode.year2021.Day18.solveAdd
import static pl.vanta.adventofcode.year2021.Day18.solveMagnitude

class Day18SolverSpec extends Specification {
    @Unroll
    def 'should solve example data - part 1, step 2 (#example)'() {
        expect:
        solveMagnitude(example) == expected

        where:
        example                                                 || expected
        '[[1,2],[[3,4],5]]'                                     || 143
        '[[[[0,7],4],[[7,8],[6,0]]],[8,1]]'                     || 1384
        '[[[[1,1],[2,2]],[3,3]],[4,4]]'                         || 445
        '[[[[3,0],[5,3]],[4,4]],[5,5]]'                         || 791
        '[[[[5,0],[7,4]],[5,5]],[6,6]]'                         || 1137
        '[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]' || 3488
    }

    def 'should solve example data - part 1'() {
        given:
        def input = parse(Day18.getResource("/2021/example-day18.txt").text)

        when:
        def step1 = solveAdd(input)

        then:
        step1 == '[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]'

        when:
        def step2 = solveMagnitude(step1)

        then:
        step2 == 4140
    }

    @Unroll
    def 'should solve example data - part 1, step 1'() {
        expect:
        solveAdd(parse(Day18.getResource("/2021/$input").text)) == expected

        where:
        input                 || expected
        'example-day18-2.txt' || '[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]'
        'example-day18-3.txt' || '[[[[1,1],[2,2]],[3,3]],[4,4]]'
        'example-day18-4.txt' || '[[[[3,0],[5,3]],[4,4]],[5,5]]'
        'example-day18-5.txt' || '[[[[5,0],[7,4]],[5,5]],[6,6]]'
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day18.getResource("/2021/day18.txt").text)) == 0
    }

    @Ignore
    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day18.getResource("/2021/day18.txt").text)) == 0
    }
}