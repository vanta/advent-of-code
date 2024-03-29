package pl.vanta.adventofcode.year2021


import spock.lang.Specification
import spock.lang.Unroll

import static pl.vanta.adventofcode.year2021.Day18.Node.add
import static pl.vanta.adventofcode.year2021.Day18.Node.fromList
import static pl.vanta.adventofcode.year2021.Day18.NodeValue
import static pl.vanta.adventofcode.year2021.Day18.addAndReduce
import static pl.vanta.adventofcode.year2021.Day18.addAndReduceAll
import static pl.vanta.adventofcode.year2021.Day18.parse
import static pl.vanta.adventofcode.year2021.Day18.parseNumber
import static pl.vanta.adventofcode.year2021.Day18.solve
import static pl.vanta.adventofcode.year2021.Day18.solve2
import static pl.vanta.adventofcode.year2021.Day18.splitNumber

class Day18SolverSpec extends Specification {
    @Unroll
    def 'should solve example data - magnitude (#list)'() {
        setup:
        def node = fromList(list)

        expect:
        node.getMagnitude() == expected

        where:
        list                                                               || expected
        [[1, 2], [[3, 4], 5]]                                              || 143
        [[[[0, 7], 4], [[7, 8], [6, 0]]], [8, 1]]                          || 1384
        [[[[1, 1], [2, 2]], [3, 3]], [4, 4]]                               || 445
        [[[[3, 0], [5, 3]], [4, 4]], [5, 5]]                               || 791
        [[[[5, 0], [7, 4]], [5, 5]], [6, 6]]                               || 1137
        [[[[8, 7], [7, 7]], [[8, 6], [7, 7]]], [[[0, 7], [6, 6]], [8, 7]]] || 3488
    }

    @Unroll
    def 'should solve example data - add & reduce (#input)'() {
        setup:
        def list = parse(Day18.getResource("/2021/$input").text)

        expect:
        addAndReduceAll(list).toList() == expected

        where:
        input                 || expected
        'example-day18-2.txt' || [[[[8, 7], [7, 7]], [[8, 6], [7, 7]]], [[[0, 7], [6, 6]], [8, 7]]]
        'example-day18-3.txt' || [[[[1, 1], [2, 2]], [3, 3]], [4, 4]]
        'example-day18-4.txt' || [[[[3, 0], [5, 3]], [4, 4]], [5, 5]]
        'example-day18-5.txt' || [[[[5, 0], [7, 4]], [5, 5]], [6, 6]]
        'example-day18-6.txt' || [[[[0, 7], 4], [[7, 8], [6, 0]]], [8, 1]]
        'example-day18-7.txt' || [[[[7, 8], [6, 6]], [[6, 0], [7, 7]]], [[[7, 8], [8, 8]], [[7, 9], [0, 6]]]]
    }

    @Unroll
    def 'should split number'() {
        expect:
        splitNumber(number) == expected

        where:
        number || expected
        10     || [5, 5]
        11     || [6, 5]
        12     || [6, 6]
    }

    @Unroll
    def 'should split list'() {
        setup:
        def node = fromList(list)

        expect:
        node.split()

        and:
        node.toList() == expected

        where:
        list                 || expected
        [10, 9]              || [[5, 5], 9]
        [9, 10]              || [9, [5, 5]]
        [10, 10]             || [[5, 5], 10]
        [[10, 10], [10, 10]] || [[[5, 5], 10], [10, 10]]
        [[9, 10], [10, 10]]  || [[9, [5, 5]], [10, 10]]
        [[9, 9], [10, 10]]   || [[9, 9], [[5, 5], 10]]
        [[9, 9], [9, 10]]    || [[9, 9], [9, [5, 5]]]
        [[9, 10], 10]        || [[9, [5, 5]], 10]
    }

    @Unroll
    def 'should not split list'() {
        setup:
        def node = fromList(list)

        expect:
        !node.split()

        and:
        node.toList() == expected

        where:
        list   || expected
        [9, 9] || [9, 9]
        [0, 0] || [0, 0]
    }

    @Unroll
    def 'should explode list: #list'() {
        setup:
        def node = fromList(list)

        expect:
        node.explode()

        and:
        node.toList() == expected

        where:
        list                                           || expected
        [[[[[9, 8], 1], 2], 3], 4]                     || [[[[0, 9], 2], 3], 4]
        [[[[[9, 8], [1, 1]], 2], 3], 4]                || [[[[0, [9, 1]], 2], 3], 4]
        [7, [6, [5, [4, [3, 2]]]]]                     || [7, [6, [5, [7, 0]]]]
        [[6, [5, [4, [3, 2]]]], 1]                     || [[6, [5, [7, 0]]], 3]
        [[3, [2, [1, [7, 3]]]], [6, [5, [4, [3, 2]]]]] || [[3, [2, [8, 0]]], [9, [5, [4, [3, 2]]]]]
        [[3, [2, [8, 0]]], [9, [5, [4, [3, 2]]]]]      || [[3, [2, [8, 0]]], [9, [5, [7, 0]]]]
    }

    @Unroll
    def 'should not explode list: #list'() {
        setup:
        def node = fromList(list)

        expect:
        !node.explode()

        and:
        node.toList() == expected

        where:
        list                  || expected
        [1, 1]                || [1, 1]
        [[1, 1], 1]           || [[1, 1], 1]
        [[[1, 1], 1], 1]      || [[[1, 1], 1], 1]
        [[[[1, 1], 1], 1], 1] || [[[[1, 1], 1], 1], 1]
        [[[[9, 8], 1], 2], 3] || [[[[9, 8], 1], 2], 3]
    }

    def 'should add nodes'() {
        given:
        def n1 = new NodeValue(1)
        def n2 = new NodeValue(2)

        when:
        def result = add(n1, n2)

        then:
        result.left == n1
        result.right == n2

        and:
        n1.parent == result
        n2.parent == result
    }

    def 'should solve example data - part 1'() {
        given:
        def input = parse(Day18.getResource("/2021/example-day18.txt").text)

        when:
        def step1 = input
                .collect { parseNumber(it) }
                .inject { a, b -> addAndReduce(a, b) }

        then:
        step1.toString() == '[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]'

        when:
        def step2 = step1.getMagnitude()

        then:
        step2 == 4140
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day18.getResource("/2021/day18.txt").text)) == 4417
    }

    def 'should solve example data - part 2'() {
        expect:
        solve2(parse(Day18.getResource("/2021/example-day18.txt").text)) == 3993
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day18.getResource("/2021/day18.txt").text)) == 4796
    }

}
