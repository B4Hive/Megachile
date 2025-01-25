package br.ufjf.b4hive.model.field;

public record Coordinate(int x, int y) {
    public int distance(Coordinate other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
