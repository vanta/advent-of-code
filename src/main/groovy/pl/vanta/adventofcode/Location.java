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
}
