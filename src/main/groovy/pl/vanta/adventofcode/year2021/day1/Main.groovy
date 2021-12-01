package pl.vanta.adventofcode.year2021.day1

import static Parser.parse
import static Solver.*


class Main {
    static void main(String[] args) {
        println solve(parse(Main.getResource("/2021/day1.txt").text))
        println solve2(parse(Main.getResource("/2021/day1.txt").text))
    }
}
