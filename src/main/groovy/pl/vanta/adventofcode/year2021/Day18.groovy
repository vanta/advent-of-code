package pl.vanta.adventofcode.year2021

import groovy.json.JsonSlurper

class Day18 {
    static List<String> parse(String input) {
        input.split('\n')
    }

    static long solve(List<String> input) {
        def numbers = input.collect { parseNumber(it) }

        println("Numbers=$numbers")
        -1
    }

    static String solveAdd(List<String> input) {
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

        println(result)
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
