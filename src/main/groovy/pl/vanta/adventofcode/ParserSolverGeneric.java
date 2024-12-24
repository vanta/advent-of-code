package pl.vanta.adventofcode;

public interface ParserSolverGeneric<T, U, V> {
    int getDayNumber();

    T parse(String lines);

    U solve(T parsedInput);

    V solve2(T parsedInput);

    default T parse2(String lines) {
        return parse(lines);
    }

    default U solveReal(T parsedInput){
        return solve(parsedInput);
    }

    default V solve2Real(T parsedInput){
        return solve2(parsedInput);
    }
}
