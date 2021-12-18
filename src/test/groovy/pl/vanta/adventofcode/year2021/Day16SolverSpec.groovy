package pl.vanta.adventofcode.year2021

import spock.lang.Specification
import spock.lang.Unroll

import static pl.vanta.adventofcode.year2021.Day16.parse
import static pl.vanta.adventofcode.year2021.Day16.solve
import static pl.vanta.adventofcode.year2021.Day16.solve2

class Day16SolverSpec extends Specification {
    @Unroll
    def 'should solve example data - part 1 (#example)'() {
        setup:
        def parsed = parse(example)

        expect:
        solve(parsed) == expected

        where:
        example                          || expected
        'D2FE28'                         || 6
        '8A004A801A8002F478'             || 16
        '620080001611562C8802118E34'     || 12
        'C0015000016115A2E0802F182340'   || 23
        'A0016C880162017C3686B18A3D4780' || 31
        '38006F45291200'                 || 9
        'EE00D40C823060'                 || 14
    }

    def 'should solve real data - part 1'() {
        expect:
        solve(parse(Day16.getResource("/2021/day16.txt").text)) == 953
    }

    @Unroll
    def 'should solve example data - part 2 (#example)'() {
        setup:
        def parsed = parse(example)

        expect:
        solve2(parsed) == expected

        where:
        example                      || expected
        'C200B40A82'                 || 3
        '04005AC33890'               || 54
        '880086C3E88112'             || 7
        'CE00C43D881120'             || 9
        'D8005AC2A8F0'               || 1
        'F600BC2D8F'                 || 0
        '9C005AC2F8F0'               || 0
        '9C0141080250320F1802104A08' || 1
    }

    def 'should solve real data - part 2'() {
        expect:
        solve2(parse(Day16.getResource("/2021/day16.txt").text)) == 246225449979
    }
}
