package br.ufjf.b4hive.model.field;

import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.inventory.Item;

public class Tile {
    private final int type;
    private Item item;
    private Entity entity;

    public Tile(int t, Item i, Entity e) {
        this.type = t;
        this.item = i;
        this.entity = e;
    }

    public Tile(int t, Entity e) {
        this.type = t;
        this.item = null;
        this.entity = e;
    }

    public Tile(int t) {
        this.type = t;
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

    public int view() {
        if (entity != null) {
            return entity.getID();
        } else if (item != null) {
            return item.getID();
        } else
            return type;
    }

    public void setItem(Item i) {
        this.item = i;
    }

    public void setEntity(Entity e) {
        this.entity = e;
    }

}
