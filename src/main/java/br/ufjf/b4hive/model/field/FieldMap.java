package br.ufjf.b4hive.model.field;

import java.util.HashMap;
import java.util.Map;

import br.ufjf.b4hive.model.entity.Breakable;
import br.ufjf.b4hive.model.entity.Entity;

public class FieldMap {
    private final Map <Coordinate, Tile> map;
    private Coordinate playerPos;

    public FieldMap(){
        this.map = new HashMap<>();
        this.playerPos = null;
    }
    public Tile getTile(Coordinate c){
        if(map.get(c) == null){
            generateTile(c);
        }
        return map.get(c);
    }
    public Tile getTile(int x, int y){
        Coordinate c = new Coordinate(x, y);
        return getTile(c);
    }
    private void generateTile(Coordinate c){
        int r = (int) (Math.random() * 10);
        Entity e = null;
        Tile tile;
        if(r < 2){ //i want to move this from here to Engine somehow
            e = new Breakable(1, "Tree");
        }
        r = (int) (Math.random() * 10);
        if(r > 8){
            tile = new Tile(1, e);
        } else{
            tile = new Tile(0, e);
        }
        map.put(c, tile);
    }
    public void setPlayerPos(Coordinate coord){
        playerPos = coord;
    }
    public Coordinate getPlayerPos(){
        return playerPos;
    }
}
