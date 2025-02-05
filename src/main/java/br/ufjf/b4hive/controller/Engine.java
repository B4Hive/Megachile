package br.ufjf.b4hive.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.entity.Bee;
import br.ufjf.b4hive.model.entity.Breakable;
import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.field.FieldMap;
import br.ufjf.b4hive.model.field.Tile;

public class Engine {

    private static boolean running = false;
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

    public static void endGame(){
        running = false;
        //aqui vai ter que salvar o jogo
    }

    public static int[][] getVisibleMap(int size){
        if(!running) System.exit(0);
        int[][] visibleMap = new int[size][size];
        
        Coordinate center = field.getPlayerPos();
        int x = center.x()-(size/2);
        int i = 0;
        int y = center.y()-(size/2);
        int j = size-1;
        while(y <= center.y() + (size/2)){
            while(x < center.x() + (size/2) + 1){
                if(field.getTile(x, y).getEntity() != null){
                    Entity e = field.getTile(x, y).getEntity();
                    if(!e.alive()){
                        //e.die();
                        field.getTile(x, y).setEntity(null);
                    }
                }
                visibleMap[i][j] = field.getTile(x, y).view();
                
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

    public static String movePlayer(char dir) {
        return movement(dir, Player());
    }

    private static String movement(char dir, Entity e){
        Coordinate pos = e.getPosition();
        Coordinate newPos;
        switch (dir) {
            case 'w' -> newPos = new Coordinate(pos.x(), pos.y()+1);
            case 'a' -> newPos = new Coordinate(pos.x()-1, pos.y());
            case 's' -> newPos = new Coordinate(pos.x(), pos.y()-1);
            case 'd' -> newPos = new Coordinate(pos.x()+1, pos.y());
            default -> newPos = pos;
        }
        if (field.getTile(newPos).getEntity() == null){
            field.getTile(newPos).setEntity(e);
            field.getTile(pos).setEntity(null);
            e.setPosition(newPos);
            if(entities.indexOf(e) == 0) field.setPlayerPos(newPos);
            return e.getName() + " moved to (" + newPos.x() + ", " + newPos.y() + ")";
        }
        if (!field.getTile(newPos).getEntity().equals(e))
            return e.getName() + " attacks, " + field.getTile(newPos).getEntity().takeDamage(e.atk());
        return null;
    }

    public static Tile generateTile(){
        int t = (int) (Math.random() * 10);
        int e = (int) (Math.random() * 10);
        Entity en = null;
        Tile tile;
        if(e < 1){
            en = new Breakable(20, "Tree");
        }
        if(t > 9){
            tile = new Tile(1);
        } else{
            tile = new Tile(0);
        }
        tile.setEntity(en);
        return tile;
    }

}
