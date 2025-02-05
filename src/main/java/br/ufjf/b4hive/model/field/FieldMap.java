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
    public Tile getTile(Coordinate c){
        if(map.get(c) == null){
            generateTile(c, new Tile(0)); //tem que mudar isso pra aleatorizar o mapa, gerando tiles diferentes com conteudo diferente
        }
        return map.get(c);
    }
    public Tile getTile(int x, int y){
        Coordinate c = new Coordinate(x, y);
        return getTile(c);
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
