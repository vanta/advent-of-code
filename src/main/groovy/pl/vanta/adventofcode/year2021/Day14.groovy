package pl.vanta.adventofcode.year2021

class Day14 {
    static Input parse(String input) {
        def lines = input.readLines()

        def pairs = getPairs(lines[0])

        def rules = lines
                .drop(2)
                .collectEntries { [it[0..1], it[6]] }

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

        def freq = countFrequency(input.getPolymer())

        LinkedHashMap sortedMap = freq.sort { a, b -> a.value <=> b.value }

        def first = sortedMap.values().first()
        def last = sortedMap.values().last()

        last - first
    }

    static int solve2(Input input) {

        0
    }

    static Map<String, Integer> countFrequency(String s) {
        def result = [:]
        s.chars.each {
            def count = result.getOrDefault(it as String, 0)
            result.put(it as String, count + 1)
        }
        result
    }

    static class Input {
        List<String> pairs
        Map<String, String> rules

        void apply() {
            pairs = pairs
                    .collect { processPair(it) }
                    .flatten()
        }

        List<String> processPair(String pair) {
            if (rules.containsKey(pair)) {
                def toInsert = rules.get(pair)
                return [pair[0] + toInsert, toInsert + pair[1]]
            }

            return [pair]
        }

        String getPolymer() {
            pairs.first()[0] + pairs.collect { it[1] }.join()
        }
    }
}
