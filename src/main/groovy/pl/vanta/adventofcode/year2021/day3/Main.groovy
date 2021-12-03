package pl.vanta.adventofcode.year2021.day3


import static pl.vanta.adventofcode.year2021.day3.Parser.parse
import static pl.vanta.adventofcode.year2021.day3.Solver.solve
import static pl.vanta.adventofcode.year2021.day3.Solver.solve2

class Main {
    static void main(String[] args) {
        println solve(parse(Main.getResource("/2021/day3.txt").text))
        println solve2(parse(Main.getResource("/2021/day3.txt").text))
    }
}
