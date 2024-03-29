package pl.vanta.adventofcode;

public interface ParserSolver<T, U> {
    int getDayNumber();

    T parse(String lines);

    U solve(T parsedInput);

    U solve2(T parsedInput);

    default T parse2(String lines) {
        return parse(lines);
    }

    default U solveReal(T parsedInput){
        return solve(parsedInput);
    }

    default U solve2Real(T parsedInput){
        return solve2(parsedInput);
    }
}
