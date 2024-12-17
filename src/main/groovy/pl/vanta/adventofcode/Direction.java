package pl.vanta.adventofcode;

public enum Direction {
    UP('^'), DOWN('v'), LEFT('<'), RIGHT('>');

    private final char symbol;

    Direction(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Direction from(char symbol) {
        return switch (symbol) {
            case '^' -> UP;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            case '>' -> RIGHT;
            default -> throw new IllegalArgumentException("Unknown direction: " + symbol);
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            case RIGHT -> DOWN;
        };
    }

    public Direction turnLeft() {
        return switch (this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            case RIGHT -> UP;
        };
    }
}
