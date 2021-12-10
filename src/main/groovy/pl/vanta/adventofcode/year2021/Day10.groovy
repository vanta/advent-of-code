package pl.vanta.adventofcode.year2021

class Day10 {
    static final OPENING = ['{', '[', '(', '<'] as Set
    static final PAIRS = ['}': '{', ']': '[', ')': '(', '>': '<']
    static final PAIRS2 = ['{': '}', '[': ']', '(': ')', '<': '>']
    static final POINTS = [')': 3, ']': 57, '}': 1197, '>': 25137]
    static final POINTS2 = [')': 1, ']': 2, '}': 3, '>': 4]

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

        index >= 0 ? POINTS.get(it[index]) : 0
    }

    static int solve2(String[] input) {
        def list = input
                .collect { getClosingSequence(it) }
                .findAll {it}
                .collect { getPoints2(it) }
                .sort()
        
        println("List=$list")
        
        list[(list.size() - 1) / 2]
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

    static String getClosingSequence(String s) {
        def stack = []

        for (int i = 0; i < s.chars.length; i++) {
            if (OPENING.contains(s.chars[i] as String)) {
                stack.push(s.chars[i])
            } else {
                def expectedOpening = PAIRS.get(s.chars[i] as String)
                def top = stack.pop()

                if (top != expectedOpening) {
                    return ''
                }

            }
        }

        stack.collect { PAIRS2.get(it.toString()) }
                .join('')
    }

    static int getPoints2(String s) {
        s.chars
                .collect{POINTS2.get(it.toString())}
                .inject(0) {acc, val -> acc * 5 + val}
    }
}
