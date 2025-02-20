package br.ufjf.b4hive.model.field;

public record Coordinate(int x, int y) {
    public int distance(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public Coordinate dislocate(char dir) {
        return switch (dir) {
            case 'w' -> new Coordinate(x, y + 1);
            case 's' -> new Coordinate(x, y - 1);
            case 'a' -> new Coordinate(x - 1, y);
            case 'd' -> new Coordinate(x + 1, y);
            default -> this;
        };
    }
}
