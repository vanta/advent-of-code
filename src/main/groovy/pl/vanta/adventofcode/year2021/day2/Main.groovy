package pl.vanta.adventofcode.year2021.day2

class Main {
    static void main(String[] args) {
        println Solver.solve(Parser.parse(Main.getResource("/2021/day2.txt").text))
        println Solver.solve2(Parser.parse(Main.getResource("/2021/day2.txt").text))
    }
}
