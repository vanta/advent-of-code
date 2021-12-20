package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    static List<Number> parse(String input) {
        input.split('\n').collect { parseNumber(it) }
    }

    static long solve(List<Number> input) {
        def result = solveAdd(input)

        solveMagnitude(result)
    }

    static String solveAdd(List<Number> input) {


        ''
    }

    static long solveMagnitude(String input) {
        def number = parseNumber(input)

        number.getMagnitude()
    }

    static long solve2(List<String> input) {
        -1
    }

    static Number parseNumber(String input) {
        def result = new JsonSlurper().parseText(input)

        parsePairNumber(result, 0, null)
    }

    static PairNumber parsePairNumber(List list, int level, Number parent) {
        def current = new PairNumber(level: level, parent: parent)

        def left = list[0] instanceof List
                ? parsePairNumber(list[0] as List, level + 1, current)
                : new RegularNumber(value: list[0] as int, level: level + 1)
        def right = list[1] instanceof List
                ? parsePairNumber(list[1] as List, level + 1, current)
                : new RegularNumber(value: list[1] as int, level: level + 1)

        current.left = left
        current.right = right

        current
    }

    static abstract class Number {
        int level
        Number parent

        abstract int getMagnitude()
    }

    static class PairNumber extends Number {
        Number left, right

        @Override
        String toString() {
            "[${left.toString()},${right.toString()}]"
        }

        @Override
        int getMagnitude() {
            3 * left.getMagnitude() + 2 * right.getMagnitude()
        }
    }

    static class RegularNumber extends Number {
        int value

        @Override
        String toString() {
            value as String
        }

        @Override
        int getMagnitude() {
            value
        }
    }

}
