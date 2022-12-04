package pl.vanta.adventofcode.year2021

class Day1Parser {
    static List<Integer> parse(String input) {
        input
                .split("\n")
                .collect { it.toInteger() }
    }
}
