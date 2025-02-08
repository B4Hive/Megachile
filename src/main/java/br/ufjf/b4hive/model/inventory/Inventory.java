package br.ufjf.b4hive.model.inventory;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
    
    private final List<Item> items;
    //private Item weapon; //type:Weapon
    //private Item armor; //type:Armor

    public Inventory() {
        this.items = new ArrayList<>();
        //this.weapon = null;
        //this.armor = null;
    }

    public String addItem(Item item){
        // add max size condition (or switch to array)
        if(item == null){
            return null;
        } else {
            items.add(item);
            organize();
            return "Added " + item.getName() + " to the inventory.";
        }
    }

    public String removeItem(Item item){
        String result;
        if(item == null){
            return null;
        }
        if(items.contains(item)){
            items.remove(item);
            result = "Removed " + item.getName() + " from the inventory.";
        } else {
            result = "Item not found in the inventory.";
        }
        return result;
    }

    public List<Item> listItems(){
        return items;
    }

    private void organize(){
        items.sort((item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
    }

}
