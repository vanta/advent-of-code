package pl.vanta.adventofcode;

public record Offset(int x, int y) {
    public Offset add(Offset offset) {
        return new Offset(x + offset.x(), y + offset.y());
    }
}
