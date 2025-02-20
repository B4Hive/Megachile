package br.ufjf.b4hive.model.inventory;

public abstract class Equipment extends Item {

    private final int value;

    public Equipment(int id, String name, int value) {
        super(id, name);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public abstract Item duplicate(int level);

}
