package br.ufjf.b4hive.model.field;

import java.util.HashMap;
import java.util.Map;

public class FieldMap {
    private final Map <Coordinate, Tile> map;
    private Coordinate playerPos;

    public FieldMap(){
        this.map = new HashMap<>();
        this.playerPos = null;
    }
    public Tile getTile(Coordinate coord){
        return map.get(coord);
    }
    public Tile getTile(int x, int y){
        return map.get(new Coordinate(x, y));
    }
    public void generateTile(Coordinate coord, Tile tile){
        map.put(coord, tile);
    }
    public void setPlayerPos(Coordinate coord){
        playerPos = coord;
    }
    public Coordinate getPlayerPos(){
        return playerPos;
    }
}
