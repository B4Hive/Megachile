package br.ufjf.b4hive.model.inventory;

public class Item {

    private final int id;
    private final String name;
    private int durability;
    private final int durabilityMax;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
        this.durabilityMax = 100;
        this.durability = this.durabilityMax;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getDurability(){
        return this.durability;
    }

    public int getDurabilityMax(){
        return this.durabilityMax;
    }

    public void lowerDurability(int amount){
        this.durability -= amount;
        if(this.durability < 0){
            this.durability = 0;
        }
    }

    public void raiseDurability(int amount){
        this.durability += amount;
        if(this.durability > this.durabilityMax){
            this.durability = this.durabilityMax;
        }
    }

    public String use(Inventory father){
        return "Cannot use this item.";
    }
}
