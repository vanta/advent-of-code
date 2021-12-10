package pl.vanta.adventofcode.year2021

class Day10 {
    static final OPENING = ['{', '[', '(', '<'] as Set
    static final PAIRS = ['}': '{', ']': '[', ')': '(', '>': '<']
    static final POINTS = [')': 3, ']': 57, '}': 1197, '>': 25137]

    static String[] parse(String input) {
        input.split('\n')
    }

    static int solve(String[] input) {
        input
                .collect { getPoints(it) }
                .sum()
    }

    private static int getPoints(String it) {
        def index = getIllegalCharIndex(it)
        if (index >= 0) {
            def illegalChar = it[index]
            POINTS.get(illegalChar)
        } else {
            0
        }
    }

    static long solve2(String[] input) {
        -1
    }

    static int getIllegalCharIndex(String s) {
        def stack = []

        for (int i = 0; i < s.chars.length; i++) {
            if (OPENING.contains(s.chars[i] as String)) {
                stack.push(s.chars[i])
            } else {
                def expectedOpening = PAIRS.get(s.chars[i] as String)

                def top = stack.pop()

                if (top != expectedOpening) {
                    return i
                }
            }
        }

        return -1
    }
}
