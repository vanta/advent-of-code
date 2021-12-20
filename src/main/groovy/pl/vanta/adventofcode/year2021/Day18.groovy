package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    static List<Number> parse(String input) {
        input.split('\n').collect { parseNumber(it) }
    }

    static long solve(List<Number> input) {
        -1
    }

    static String solveAdd(List<Number> input) {
        def numbers = input.collect { parseNumber(it) }

        println("Numbers=$numbers")
        -1


        ''
    }

    static long solveMagnitude(String input) {
        -1
    }

    static long solve2(List<String> input) {
        -1
    }

    static Number parseNumber(String input) {
        def slurper = new JsonSlurper()
        def result = slurper.parseText(input)

        parseNumber(result, 0)
    }

    static PairNumber parseNumber(List list, int level) {
        def left = list[0] instanceof List
                ? parseNumber(list[0] as List, level + 1)
                : new RegularNumber(value: list[0] as int, level: level + 1)
        def right = list[1] instanceof List
                ? parseNumber(list[1] as List, level + 1)
                : new RegularNumber(value: list[1] as int, level: level + 1)

        new PairNumber(left: left, right: right, level: level)
    }

    static class Number {
        int level
    }

    static class PairNumber extends Number {
        Number left, right

        @Override
        String toString() {
            "[${left.toString()},${right.toString()}]"
        }
    }

    static class RegularNumber extends Number {
        int value

        @Override
        String toString() {
            value as String
        }
    }

}
