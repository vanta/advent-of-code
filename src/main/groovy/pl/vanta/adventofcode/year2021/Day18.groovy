package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    static List<PairNumber> parse(String input) {
        input.split('\n').collect { parseNumber(it) }
    }

    static long solve(List<PairNumber> input) {
        def result = solveAdd(input)
        solveMagnitude(result)
    }

    static String solveAdd(List<PairNumber> input) {
        def result = input.inject { a, b -> a.add(b).reduce() }

        result.toString()
    }

    static long solveMagnitude(String input) {
        def number = parseNumber(input)

        number.getMagnitude()
    }

    static long solve2(List<String> input) {
        -1
    }

    static PairNumber parseNumber(String input) {
        def result = new JsonSlurper().parseText(input)

        parsePairNumber(result, 0, null)
    }

    static PairNumber parsePairNumber(List list, int level, Number parent) {
        def current = new PairNumber(level: level, parent: parent)

        def left = list[0] instanceof List
                ? parsePairNumber(list[0] as List, level + 1, current)
                : new RegularNumber(value: list[0] as int, level: level + 1, parent: parent)
        def right = list[1] instanceof List
                ? parsePairNumber(list[1] as List, level + 1, current)
                : new RegularNumber(value: list[1] as int, level: level + 1, parent: parent)

        current.left = left
        current.right = right

        current
    }

    static abstract class Number {
        int level
        Number parent

        abstract int getMagnitude()

        def increaseLevel() {
            level++
        }

        def split() {
            null
        }
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

        @Override
        def increaseLevel() {
            super.increaseLevel()

            left.increaseLevel()
            right.increaseLevel()
        }

        PairNumber add(Number otherNumber) {
            def result = new PairNumber(
                    parent: null,
                    level: 0
            )

            this.parent = result
            otherNumber.parent = result

            this.increaseLevel()
            otherNumber.increaseLevel()

            result.left = this
            result.right = otherNumber

            result
        }

        PairNumber reduce() {
            def canBeReduced = true

            while (canBeReduced) {
                canBeReduced = explode() || split()
            }

            this
        }

        boolean explode() {
            left.explode

            if (left.level >= 4) {
                left = new RegularNumber(value: 0, level: 4)
            }
            if (right.level >= 4) {

            }
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

        PairNumber split() {
            def decimal = (value / 2) as int
            new PairNumber(
                    left: new RegularNumber(value: decimal),
                    right: new RegularNumber(value: value - decimal),
                    level: this.level,
                    parent: this.parent
            )
        }
    }

}
