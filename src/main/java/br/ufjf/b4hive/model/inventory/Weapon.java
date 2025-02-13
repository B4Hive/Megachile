package br.ufjf.b4hive.model.inventory;

public class Weapon extends Equipment{
    
    private Ability ability;

    public Weapon(int id, String name, int value) {
        super(id, name, value);
    }

    @Override
    public String use(Inventory inventory, int n) {
        if(inventory.getHand() == this){
            // use weapon == use weapon's ability
            return "NYI";
        }
        inventory.holdItem(this);
        inventory.removeItem(n);
        return "You equipped the " + this.getName();
    }

    @Override
    public String getInfo(){
        return super.getInfo() + " - Damage: " + getValue();
    }
    
}
