package br.ufjf.b4hive.model.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    
    private final Map<Item, Integer> items;
    //private Item weapon; //type:Weapon
    //private Item armor; //type:Armor

    public Inventory() {
        this.items = new HashMap<>();
        //this.weapon = null;
        //this.armor = null;
    }

    public String addItem(Item item){
        if(item == null){
            return null;
        }
        if(items.containsKey(item)){
            items.put(item, items.get(item) + 1);
            return "Added another " + item.getName() + " to the inventory.";
        } else {
            items.put(item, 1);
            return "Added " + item.getName() + " to the inventory.";
        }
    }

    public String removeItem(Item item){
        String result;
        if(item == null){
            return null;
        }
        if(items.containsKey(item)){
            if(items.get(item) > 1){
                items.put(item, items.get(item) - 1);
                result = "Removed one " + item.getName() + " from the inventory.";
            } else {
                items.remove(item);
                result = "Removed " + item.getName() + " from the inventory.";
            }
        } else {
            result = "Item not found in the inventory.";
        }
        return result;
    }

}
