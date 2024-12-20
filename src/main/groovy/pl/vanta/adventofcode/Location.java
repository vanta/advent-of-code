package pl.vanta.adventofcode;

import java.util.Set;

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
}
