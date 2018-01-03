package pl.vanta.adventofcode.year2017.day2


class Solver2 {
    static int solve(String input) {
        input
                .split("\n")
                .collect {it.split("\\s")}
                .collect {it*.toInteger()}
                .collect {findDiff(it)}
                .sum()
    }

    static int findDiff(List numbers) {
        numbers.with {
            max() - min()
        }
    }
}
