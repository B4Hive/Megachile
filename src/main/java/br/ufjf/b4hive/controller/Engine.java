package br.ufjf.b4hive.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.entity.Bee;
import br.ufjf.b4hive.model.entity.Breakable;
import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.field.FieldMap;
import br.ufjf.b4hive.model.field.Tile;
import br.ufjf.b4hive.model.inventory.Item;
import br.ufjf.b4hive.model.inventory.Weapon;
import br.ufjf.b4hive.model.inventory.Armor;

public class Engine {

    private static boolean running = false;
    private static FieldMap field;
    private static Bee player;
    
    public static void newGame(){
        running = true;
        field = new FieldMap();
        player = new Bee(10, "@Player", new Weapon(40, "Weapon", 4), new Armor(41, "Armor", 10));
        player.setPosition(new Coordinate(0, 0));
        field.getTile(player.getPosition()).setEntity(player);
    }

    public static void endGame(){
        running = false;
        // save the game here
    }

    public static int[][] getVisibleMap(int size){
        if(!running) System.exit(0);

        int[][] visibleMap = new int[size][size];
        
        Coordinate center = player.getPosition();
        int x = center.x()-(size/2);
        int i = 0;
        int y = center.y()-(size/2);
        int j = size-1;
        while(y <= center.y() + (size/2)){
            while(x < center.x() + (size/2) + 1){
                if(field.getTile(x, y).getEntity() != null){
                    Entity e = field.getTile(x, y).getEntity();
                    if(!e.alive()){
                        Item it = e.drop((int) (Math.random() * 100));
                        field.getTile(x, y).setItem(it);
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

    public static Tile generateTile(){
        if(!running) System.exit(0);

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

    public static String movePlayer(char dir) {
        return movement(dir, player);
    }

    private static String movement(char dir, Entity e){
        if(!running) System.exit(0);

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
            return e.getName() + " moved to (" + newPos.x() + ", " + newPos.y() + ")";
        }
        if (!field.getTile(newPos).getEntity().equals(e))
            return e.getName() + " attacks, " + field.getTile(newPos).getEntity().takeDamage(e.atk());
        return null;
    }

    public static String takeItem(){
        if(!running) System.exit(0);

        Coordinate pos = player.getPosition();
        Tile tile = field.getTile(pos);
        if(tile.getItem() != null){
            Item item = tile.getItem();
            tile.setItem(null);
            return player.takeItem(item);
        }
        return null;
    }

    public static List<String> listPlayerInventory(){
        if(!running) System.exit(0);

        List<String> inv = new ArrayList<>();
        for(int i = 0; i < player.getInventory().getSize(); i++){
            Item item = player.getInventory().getItem(i);
            String temp;
            if(item == null) temp = "Empty";
            else temp = item.getInfo();
            inv.add(temp);
        }
        return inv;
    }
    
    public static String playerItemInfo(int n){
        if(!running) System.exit(0);

        Item item = player.getInventory().getItem(n);
        if(item == null) return "Empty";
        return item.getInfo();
    }

    public static String useItem(int n){
        if(!running) System.exit(0);

        if(player.getInventory().getItem(n) == null) return null;
        return player.useItem(n);
    }

    public static String dropItem(int n){
        if(!running) System.exit(0);

        if(player.getInventory().getItem(n) == null) return null;
        Coordinate pos = player.getPosition();
        if(field.getTile(pos).getItem() != null) return "Can't drop item, there is already an item in the tile.";
        Item item = player.drop(n);
        String temp = player.getName() + " dropped " + item.getName() + ".";
        field.getTile(pos).setItem(item);
        return temp;
    }
}
