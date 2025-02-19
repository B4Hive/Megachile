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

        Effect tempEffect = new Effect(50, "dot",0.5f, 3);
        effects.add(tempEffect);
        Ability tempAbility = new Ability(5, tempEffect);
        Weapon tempWeapon = new Weapon(40, "Stinger", 5, tempAbility);
        items.add(tempWeapon);

        tempEffect = new Effect(52, "hot", 0.5f, 3);
        effects.add(tempEffect);
        tempAbility = new Ability(6, tempEffect);
        tempWeapon = new Weapon(43, "Healing Staff", 5, tempAbility);
        items.add(tempWeapon);

        Armor tempArmor = new Armor(41, "Armor", 5);
        items.add(tempArmor);

        Item tempItem = new Item(10, "Pollem");
        items.add(tempItem);
        tempItem = new Item(11, "Nectar");
        items.add(tempItem);
        tempItem = new Item(12, "Leaf");
        items.add(tempItem);
        tempItem = new Item(13, "Resin");
        items.add(tempItem);
        tempItem = new Item(14, "Petal");
        items.add(tempItem);
        tempItem = new Item(15, "Fruit");
        items.add(tempItem);
        tempItem = new Item(16, "Water");
        items.add(tempItem);
        tempItem = new Item(17, "Exoskeleton");
        items.add(tempItem);

        Breakable tempBreakable = new Breakable(20, "Flower");
        tempBreakable.getInventory().addItem(getItem(10));
        tempBreakable.getInventory().addItem(getItem(11));
        tempBreakable.getInventory().addItem(getItem(12));
        tempBreakable.getInventory().addItem(getItem(14));
        entities.add(tempBreakable);
        tempBreakable = new Breakable(21, "Tree");
        tempBreakable.getInventory().addItem(getItem(12));
        tempBreakable.getInventory().addItem(getItem(13));
        tempBreakable.getInventory().addItem(getItem(15));
        entities.add(tempBreakable);
        tempBreakable = new Breakable(22, "Nest");
        entities.add(tempBreakable);

        tempBreakable = new Breakable(90, "Enemy"); // enemy
        tempBreakable.getInventory().addItem(getItem(17));
        // tempBreakable.getInventory().holdItem(getItem(40));
        // tempBreakable.getInventory().useItem(getItem(41));
        entities.add(tempBreakable);

    }

    public static Entity getRandEntity(){
        return entities.get((int)(Math.random() * entities.size())).duplicate();
    }

    public static Item getRandItem(){
        return items.get((int)(Math.random() * items.size())).duplicate();
    }

    public static Item getItem(int id){
        for(Item i : items){
            if(i.getID() == id) return i.duplicate();
        }
        return null;
    }
    public static Effect getRandEffect(){
        return effects.get((int)(Math.random() * effects.size())).duplicate();
    }

}
