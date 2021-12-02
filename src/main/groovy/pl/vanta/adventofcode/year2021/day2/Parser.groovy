package pl.vanta.adventofcode.year2021.day2

class Parser {
    static List<Tuple2<String, Integer>> parse(String input) {
        input
                .split("\n")
                .collect { it.split(" ") }
                .collect { new Tuple2<String, Integer>(it[0], it[1].toInteger()) }
    }
}
