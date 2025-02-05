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
        field.getTile(Player().getPosition()).setEntity(Player());
    }

    public static int[][] getVisibleMap(int size){
        if(!running) System.exit(0);
        int[][] visibleMap = new int[size][size];
        
        Coordinate center = field.getPlayerPos();
        int x = center.x()-(size/2);
        int i = 0;
        int y = center.y()-(size/2);
        int j = size-1;
        while(y < center.y() + (size/2)){
            while(x < center.x() + (size/2)){
                int tile = field.getTile(x, y).getTopID();
                visibleMap[i][j] = tile;
                x++;
                i++;
            }
            x = center.x()-(size/2);
            i = 0;
            y++;
            j--;
        }
        
        return visibleMap;
    }

}
