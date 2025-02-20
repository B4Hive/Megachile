package br.ufjf.b4hive.model.entity;

public class Breakable extends Entity {

    public Breakable(int id, String name) {
        super(id, name);
    }

    @Override
    public int atk() {
        return 0;
    }

    @Override
    public Breakable duplicate() {
        Breakable e = new Breakable(this.getID(), this.getName());
        e.setInventory(this.getInventory().duplicate());
        return e;
    }

}
