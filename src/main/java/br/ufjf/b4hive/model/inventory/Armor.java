package br.ufjf.b4hive.model.inventory;

public class Armor extends Equipment{

    public Armor(int id, String name, int value) {
        super(id, name, value);
    }

    @Override
    public String use(Inventory father) {
        if(father.getBody() == null){
            father.addItem(father.getBody());
            father.setBody(this);
            return "You equipped the " + this.getName() + "!";
        }
        return "You already have an armor equipped!";
    }

}

