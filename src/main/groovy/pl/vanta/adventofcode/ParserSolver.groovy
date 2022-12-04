package pl.vanta.adventofcode

interface ParserSolver<T, U> {
    int getDayNumber()
    T parse(String lines)
    U solve(T parsedInput)
    U solve2(T parsedInput)
    U getExampleAnswer1()
    U getExampleAnswer2()
    U getRealAnswer1()
    U getRealAnswer2()
}
