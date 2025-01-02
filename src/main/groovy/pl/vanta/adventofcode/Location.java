package pl.vanta.adventofcode;

import java.util.Set;

import static java.lang.Math.*;

public record Location(int x, int y) {
    public Location up() {
        return new Location(x - 1, y);
    }

    public Location down() {
        return new Location(x + 1, y);
    }

    public Location left() {
        return new Location(x, y - 1);
    }

    public Location right() {
        return new Location(x, y + 1);
    }

    public Set<Location> neighbours() {
        return Set.of(up(), down(), left(), right());
    }

    public Set<Location> allNeighbours() {
        return Set.of(up(), down(), left(), right(), up().left(), up().right(), down().left(), down().right());
    }

    public Location move(int dx, int dy) {
        return new Location(x + dx, y + dy);
    }

    public Location teleport(int sizeX, int sizeY) {
        int newX = x;
        int newY = y;

        if (newX < 0) {
            newX += sizeX;
        } else if (newX >= sizeX) {
            newX -= sizeX;
        }

        if (newY < 0) {
            newY += sizeY;
        } else if (newY >= sizeY) {
            newY -= sizeY;
        }

        return new Location(newX, newY);
    }

    public Location move(Direction direction) {
        return move(direction.getSymbol());
    }

    public Location move(char direction) {
        return switch (direction) {
            case '^' -> this.up();
            case 'v' -> this.down();
            case '<' -> this.left();
            case '>' -> this.right();
            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }

    public Location move(Offset offset) {
        return new Location(x + offset.x(), y + offset.y());
    }

    public int taxiDistance(Location other) {
        return abs(x - other.x) + abs(y - other.y);
    }

    public boolean isDiagonalNeighbour(Location other) {
        return abs(x - other.x) == 1 && abs(y - other.y) == 1;
    }

    public boolean inLine(Location other) {
        return x == other.x || y == other.y;
    }

    public Offset offset(Location other) {
        return new Offset(other.x - x, other.y - y);
    }
}
