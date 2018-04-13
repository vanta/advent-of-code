package pl.vanta.adventofcode.year2017.day3

import static java.lang.Math.abs
import static java.lang.Math.ceil
import static java.lang.Math.floor
import static java.lang.Math.pow
import static java.lang.Math.sqrt

class Solver3 {
    static int solve(String input) {
        def i = input.toInteger()

        i == 1 ? 0 : calculate(i)
    }

    static int calculate(int i) {
        def d = sqrt(i)
        def ceila = ceil(d)
        def circle = floor(ceila / 2d)
        long circleMax = pow(2 * (circle + 1) - 1, 2).toInteger()
        long circleMin = pow(2 * circle - 1, 2).toInteger() + 1
        long circleSize = circleMax - circleMin + 1
        long circleSide = circleSize / 4
        long dev = abs((circleSide / 2) - abs(i % circleSide - 1))

        circle + dev
    }

}
