package br.ufjf.b4hive.model.field;

import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.inventory.Item;

public class Tile {
    private final int type;
    private Item item;
    private Entity entity;

    public Tile(int type, Item item, Entity entity) {
        this.type = type;
        this.item = item;
        this.entity = entity;
    }
    public Tile(int type) {
        this.type = type;
        this.item = null;
        this.entity = null;
    }
    public int getType() {
        return type;
    }
    public Item getItem() {
        return item;
    }
    public Entity getEntity() {
        return entity;
    }
    public int getTopID(){
        if (entity != null){
            return entity.getID();
        } else if(item != null){
            //return item.getID();
        }
        return type;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
