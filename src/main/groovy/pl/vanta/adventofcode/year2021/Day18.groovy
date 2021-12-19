package pl.vanta.adventofcode.year2021

class Day18 {
    static List<String> parse(String input) {
        input.split('\n')
    }

    static long solve(List<String> input) {
        def numbers = input.collect { parseNumber(it) }


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

    }

    static class Number {

    }

    static class PairNumber extends Number {
        Number left, right
    }

    static class RegularNumber extends Number {
        int value
    }

}
