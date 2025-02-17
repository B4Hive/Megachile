package br.ufjf.b4hive.model.inventory;

import java.util.ArrayList;
import java.util.List;


public class Inventory {
    
    private final List<Item> items;
    private Item hand; //type:Weapon
    private Armor body;

    public Inventory() {
        this.items = new ArrayList<>();
        this.hand = null;
        this.body = null;
    }
    
    public Item getHand() {
        return hand;
    }

    public void holdItem(Item item){
        if (this.hand == null){
            this.hand = item;
            this.items.remove(item);
        }
        else {
            Item temp = this.hand;
            this.hand = item;
            this.items.remove(item);
            this.items.add(temp);
        }
    }

    public Item getBody() {
        return body;
    }

    public void setBody(Armor body) {
        this.body = body;
    }

    public Item getItem(int index){
        if(index < 0 || index >= getSize()){
            return null;
        } else if(index == 0)
            return getHand();
        else if(index == 1)
            return getBody();
        return items.get(index - 2);
    }

    public Item dropItem(int index){
        if(index < 0 || index >= getSize()){
            return null;
        } else if(index == 0){
            Item temp = hand;
            hand = null;
            return temp;
        } else if(index == 1){
            Item temp = body;
            body = null;
            return temp;
        }
        Item temp = items.get(index - 2);
        items.remove(index - 2);
        return temp;
    }

    public int getSize(){
        return items.size() + 2;
    }

    public boolean isFull(){
        return getSize() >= 10;
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

    public String removeItem(int index){
        String result;
        if(getItem(index) == null){
            result = "Item not found in the inventory.";
        } else {
            result = "Destroyed " + getItem(index).getName() + " from the inventory.";
            items.remove(index - 2);
        }
        return result;
    }

    private void organize(){
        items.sort((item1, item2) -> item1.getName().compareToIgnoreCase(item2.getName()));
    }

    public void tickCooldown() {
        if (this.getHand() instanceof Weapon weapon)
            weapon.getAbility().tickCooldown();
    }

}
