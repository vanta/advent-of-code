package pl.vanta.adventofcode.year2021

class Day14 {
    static Input parse(String input) {
        def lines = input.readLines()

        def pairs = getPairs(lines[0])

        def rules = lines
                .drop(2)
                .collect { new Rule(from: it[0..1], to: it[6]) }

        new Input(pairs: pairs, rules: rules)
    }

    private static List<String> getPairs(String line) {
        List<String> result = []

        for (int i = 0; i < line.chars.length - 1; i++) {
            result.add(line[i] + line[i + 1])
        }

        result
    }

    static int solve(Input input) {


        (0..9).each {
            input.apply()
        }

        0
    }

    static int solve2(Input input) {

        0
    }

    static class Input {
        List<String> pairs
        List<Rule> rules

        void apply() {
            pairs.collect {}
        }
    }

    static class Rule {
        String from
        String to
    }
}
