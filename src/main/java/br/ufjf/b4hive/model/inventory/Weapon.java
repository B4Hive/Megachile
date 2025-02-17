package br.ufjf.b4hive.model.inventory;

public class Weapon extends Equipment{
    
    private final Ability ability;

    public Weapon(int id, String name, int value) {
        super(id, name, value);
        this.ability = new Ability(5, new Effect(111, 1.5f, 3));
    }

    @Override
    public String use(Inventory inventory, int n) {
        if(inventory.getHand() == this){
            // use weapon == use weapon's ability
            return "Already equipped.";
        }
        inventory.holdItem(this);
        inventory.removeItem(n);
        return "You equipped the " + this.getName();
    }

    @Override
    public String getInfo(){
        return super.getInfo() + " - Damage: " + getValue();
    }
    
    public Effect useAbility(Inventory inventory){
        if(inventory.getHand() == this){
            return ability.use();
        }
        return null;
    }
}
