package br.ufjf.b4hive.model;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.entity.Breakable;
import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.inventory.Ability;
import br.ufjf.b4hive.model.inventory.Armor;
import br.ufjf.b4hive.model.inventory.Effect;
import br.ufjf.b4hive.model.inventory.Item;
import br.ufjf.b4hive.model.inventory.Weapon;

public class DataBank {

    private static final List<Entity> entities = new ArrayList<>();
    private static final List<Item> items = new ArrayList<>();
    private static final List<Effect> effects = new ArrayList<>();

    public static void initData(){

        Effect tempEffect = new Effect(51, "dot",0.5f, 3);
        effects.add(tempEffect);
        Ability tempAbility = new Ability(5, tempEffect);
        Weapon tempWeapon = new Weapon(40, "Weapon", 5, tempAbility);
        items.add(tempWeapon);

        tempEffect = new Effect(52, "hot", 0.5f, 3);
        effects.add(tempEffect);
        tempAbility = new Ability(6, tempEffect);
        tempWeapon = new Weapon(41, "Weapon2", 5, tempAbility);
        items.add(tempWeapon);

        Armor tempArmor = new Armor(41, "Armor", 5);
        items.add(tempArmor);
        tempArmor = new Armor(42, "Armor2", 10);
        items.add(tempArmor);

        Item tempItem = new Item(30, "Item");
        items.add(tempItem);
        tempItem = new Item(31, "Item2");
        items.add(tempItem);

        for(int i = 0; i < 50; i++){
            Breakable tempBreakable = new Breakable(20, "Breakable");
            for(int j = 0; j < 5; j++) tempBreakable.getInventory().addItem(getRandItem());
            entities.add(tempBreakable);
        }

    }

    public static Entity getRandEntity(){
        return entities.get((int)(Math.random() * entities.size()));
    }

    public static Item getRandItem(){
        return items.get((int)(Math.random() * items.size()));
    }

    public static Item getItem(int id){
        for(Item i : items){
            if(i.getID() == id) return i;
        }
        return null;
    }
    public static Effect getRandEffect(){
        return effects.get((int)(Math.random() * effects.size()));
    }

}
