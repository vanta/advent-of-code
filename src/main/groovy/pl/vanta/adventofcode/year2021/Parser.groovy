package pl.vanta.adventofcode.year2021

class Parser {
    static List<Integer> parse(String input) {
        input
                .split("\n")
                .collect { it.toInteger() }
    }
}
