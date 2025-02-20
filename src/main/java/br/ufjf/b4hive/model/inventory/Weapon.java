package br.ufjf.b4hive.model.inventory;

public class Weapon extends Equipment {

    private final Ability ability;

    public Weapon(int id, String name, int value, Ability ability) {
        super(id, name, value);
        this.ability = ability;
    }

    @Override
    public String use(Inventory inventory, int n) {
        if (inventory.getHand() == this) {
            return "Already equipped.";
        }
        inventory.holdItem(this);
        inventory.removeItem(n);
        return "You equipped the " + this.getName();
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " - Damage: " + getValue() + " - " + ability.getInfo();
    }

    public Ability getAbility() {
        return ability;
    }

    public Effect useAbility(Inventory inventory) {
        if (inventory.getHand() == this) {
            return ability.use(getValue());
        }
        return null;
    }

    @Override
    public Item duplicate() {
        return new Weapon(getID(), getName(), getValue(),
                new Ability(ability.getCooldownMax(), ability.getEffect().duplicate()));
    }

    @Override
    public Item duplicate(int level) {
        return new Weapon(getID(), getName(), (getValue() + level),
                new Ability(ability.getCooldownMax(), ability.getEffect().duplicate()));
    }
}
