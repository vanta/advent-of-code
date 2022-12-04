package pl.vanta.adventofcode.year2022

import pl.vanta.adventofcode.ParserSolver

class Day1 implements ParserSolver<List<List<String>>, Integer> {
    @Override
    int getDayNumber() {
        1
    }

    @Override
    List<List<String>> parse(final String lines) {
        lines.split('\n\n')
                .collect { it.readLines() }
    }

    @Override
    Integer solve(final List<List<String>> parsedInput) {
        parsedInput
                .collect { it.collect { it as Integer }.sum() }
                .max()
    }

    @Override
    Integer solve2(final List<List<String>> parsedInput) {
        parsedInput
                .collect { it.collect { it as Integer }.sum() }
                .sort()
                .reverse()
                .subList(0, 3)
                .sum()
    }

    @Override
    Integer getExampleAnswer1() {
        24000
    }

    @Override
    Integer getExampleAnswer2() {
        45000
    }

    @Override
    Integer getRealAnswer1() {
        67016
    }

    @Override
    Integer getRealAnswer2() {
        200116
    }
}
