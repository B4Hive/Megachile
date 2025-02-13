package br.ufjf.b4hive.model.entity;

import br.ufjf.b4hive.model.inventory.Item;
import br.ufjf.b4hive.model.inventory.Weapon;
import br.ufjf.b4hive.model.inventory.Armor;

public class Bee extends Entity{

    public Bee(int id, String name) {
        super(id, name);
    }

    public Bee(int id, String name, Weapon weapon, Armor armor){
        super(id, name, weapon, armor);
    }

    public String takeItem(Item item){
        String result = this.getName() + " took " + item.getName() + ". ";
        result += getInventory().addItem(item);
        return result;
    }

    @Override
    public Item drop(int chance){
        return getInventory().dropItem(chance);
    }

    public String useItem(int n) {
        return getInventory().getItem(n).use(getInventory());
    }

}
