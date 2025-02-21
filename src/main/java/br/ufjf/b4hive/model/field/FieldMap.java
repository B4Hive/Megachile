package br.ufjf.b4hive.model.field;

import java.util.HashMap;
import java.util.Map;

import br.ufjf.b4hive.controller.Engine;

public class FieldMap {
    private final Map<Coordinate, Tile> map;

    public FieldMap() {
        this.map = new HashMap<>();
    }

    public Tile getTile(Coordinate c) {
        if (!map.containsKey(c)) {
            generateTile(c);
        }
        return map.get(c);
    }

    public Tile getTile(int x, int y) {
        Coordinate c = new Coordinate(x, y);
        return getTile(c);
    }

    private void generateTile(Coordinate c) {
        map.put(c, Engine.generateTile());
    }

}
