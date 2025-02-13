package br.ufjf.b4hive.model.inventory;

public class Weapon extends Equipment{
    
    public Weapon(int id, String name, int value) {
        super(id, name, value);
    }

    @Override
    public String use(Inventory father) {
        // use weapon == use weapon's ability
        return null;
    }

    @Override
    public String getInfo(){
        return super.getInfo() + " - Damage: " + getValue();
    }
    
}
