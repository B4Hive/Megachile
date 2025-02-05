package br.ufjf.b4hive.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.entity.Bee;
import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.field.FieldMap;

public class Engine {

    private static boolean running = true;
    private static List<Entity> entities;
    private static FieldMap field;

    private static Entity Player(){
        return entities.get(0);
    }
    
    public static void newGame(){
        running = true;
        field = new FieldMap();
        entities = new ArrayList<>();
        entities.add(new Bee(10, "@Player"));
        Player().setPosition(new Coordinate(0, 0));
        field.setPlayerPos(Player().getPosition());
    }

    public static int[][] getVisibleMap(int size){
        if(!running) System.exit(0);
        int[][] visibleMap = new int[size][size];
        for(int i = 1+(size/2); i > -size/2; i--){
            for(int j = -size/2; j < 1+(size/2); j++){
                Coordinate center = field.getPlayerPos();
                int tile = field.getTile(center.x() + i, center.y() + j).getTopID();
                visibleMap[i + size/2][j + size/2] = tile;
            }
        }
        return visibleMap;
    }

}
