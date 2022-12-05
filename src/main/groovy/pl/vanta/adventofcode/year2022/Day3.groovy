package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.ParserSolver

class Day3 implements ParserSolver<List<String>, Integer> {
    @Override
    int getDayNumber() {
        3
    }

    @Override
    List<String> parse(final String lines) {
        lines.split('\n') as List
    }

    @Override
    Integer solve(final List<String> parsedInput) {
        parsedInput
                .collect { new Tuple2<String, String>(it.substring(0, it.length() / 2 as int), it.substring(it.length() / 2 as int)) }
                .collect { new Tuple2<Set, Set>(it.first.chars as Set, (it.second.chars as Set)) }
                .collect { getCommon(it.first, it.second) }
                .collect { it.first() }
                .collect { getValue(it) }
                .sum() as Integer
    }

    @Override
    Integer solve2(final List<String> parsedInput) {
        def result = []
        for (int i = 0; i < parsedInput.size(); i += 3) {
            result << [parsedInput[i + 0], parsedInput[i + 1], parsedInput[i + 2]]
        }

        result
                .collect { getCommon(it[0].chars as Set, it[1].chars as Set, it[2].chars as Set) }
                .collect { it.first() }
                .collect { getValue(it) }
                .sum() as Integer
    }

    static int getValue(Character c) {
        if (c.isLowerCase()) {
            return c.charValue() - 96
        } else if (c.isUpperCase()) {
            return c.charValue() - 38
        } else {
            return -1
        }
    }

    static Set<Character> getCommon(Set<Character> s1, Set<Character> s2) {
        s1.intersect(s2)
    }

    static Set<Character> getCommon(Set<Character> s1, Set<Character> s2, Set<Character> s3) {
        s1.intersect(s2).intersect(s3)
    }

}
