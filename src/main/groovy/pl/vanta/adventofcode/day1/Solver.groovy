package pl.vanta.adventofcode.day1


class Solver {
    static pattern = /.*?(.)(\1+).*?/

    static int solve(String input) {
        def result = findGroups(input)
                .collect {it - it[0]}
                .collect {[size: it.size(), val: it[0] as Integer]}
                .collect {it.size * it.val}
                .sum(0)

        result + (startEndTheSame(input) ? input[0] as Integer : 0)
    }

    static boolean startEndTheSame(String input) {
        input[0] == input[input.size() - 1]
    }

    static findGroups(String str) {
        (str =~ pattern).collect {it[1] + it[2]}
    }
}
