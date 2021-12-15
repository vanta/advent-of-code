package pl.vanta.adventofcode.year2021

class Day14 {
    static Input parse(String input) {
        def lines = input.readLines()

        def rules = lines
                .drop(2)
                .collectEntries { [it[0..1], it[6]] }

        new Input(lines[0], rules)
    }

    static int solve(Input input) {
        (1..10).each {
            input.apply()
        }

        LinkedHashMap sortedMap = input.frequency.sort { a, b -> a.value <=> b.value }

        def first = sortedMap.values().first()
        def last = sortedMap.values().last()

        last - first
    }

    static int solve2(Input input) {
        (1..40).each {
            input.apply()
        }

        LinkedHashMap sortedMap = input.frequency.sort { a, b -> a.value <=> b.value }

        def first = sortedMap.values().first()
        def last = sortedMap.values().last()

        last - first
    }

    static class Input {
        String polymer
        Map<String, Long> pairs
        Map<String, String> injections
        Map<String, Long> frequency

        Input(String polymer, Map<String, String> injections) {
            this.polymer = polymer
            this.injections = injections
            this.pairs = buildPairs()
            this.frequency = buildFrequency()
        }

        private Map<String, Long> buildPairs() {
            Map<String, Long> result = [:]

            for (int i = 0; i < polymer.chars.length - 1; i++) {
                def pair = polymer[i] + polymer[i + 1]
                long count = result.getOrDefault(pair, 0L)

                result.put(pair, count + 1)
            }

            result
        }

        void apply() {
            Map<String, Long> newPairs = [:]

            pairs.each { entry ->
                def toInject = injections.get(entry.key)

                if (toInject) {
                    def newPair1 = entry.key[0] + toInject
                    def newPair2 = toInject + entry.key[1]

                    frequency.merge(toInject, entry.value, { v1, v2 -> v1 + v2 })

                    newPairs.merge(newPair1, entry.value, { v1, v2 -> v1 + v2 })
                    newPairs.merge(newPair2, entry.value, { v1, v2 -> v1 + v2 })
                } else {
                    newPairs.merge(entry.key, entry.value, { v1, v2 -> v1 + v2 })
                }
            }

            pairs = newPairs
        }

        Map<String, Long> buildFrequency() {
            def result = [:]
            polymer.chars.each {
                result.merge(it as String, 1, { v1, v2 -> v1 + v2 })
            }
            result
        }
    }
}
