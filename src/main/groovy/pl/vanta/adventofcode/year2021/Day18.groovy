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

        aaa(result)
    }

    static PairNumber aaa(List list) {
        def left = list[0] instanceof List
                ? aaa(list[0] as List)
                : new RegularNumber(value: list[0] as int)
        def right = list[1] instanceof List
                ? aaa(list[1] as List)
                : new RegularNumber(value: list[1] as int)

        new PairNumber(left: left, right: right)
    }

    static class Number {

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
