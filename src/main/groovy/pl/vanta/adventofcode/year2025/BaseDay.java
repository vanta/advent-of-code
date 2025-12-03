package pl.vanta.adventofcode.year2025;

import pl.vanta.adventofcode.ParserSolver;
import pl.vanta.adventofcode.ParserSolverGeneric;

public abstract class BaseDay<T, U, V> implements ParserSolver<T, U> {
    @Override
    public int getYearNumber() {
        return 2025;
    }
}
