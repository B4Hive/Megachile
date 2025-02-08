package br.ufjf.b4hive.model.entity;

import br.ufjf.b4hive.model.inventory.Item;

public class Bee extends Entity{ 
    public Bee(int id, String name) {
        super(id, name);
    }
    
    @Override
    public int atk(){
        return 5; // add the atk modifiers like weapon
    }

    public String takeItem(Item item){
        String result = this.getName() + " took " + item.getName() + ". ";
        result += getInventory().addItem(item);
        return result;
    }
}
