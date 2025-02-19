package br.ufjf.b4hive.model.inventory;

public class Armor extends Equipment{

    public Armor(int id, String name, int value) {
        super(id, name, value);
    }

    @Override
    public String use(Inventory father, int n) {
        if(father.getBody() == null){
            father.addItem(father.getBody());
            father.setBody(this);
            father.removeItem(n);
            return "You equipped the " + this.getName();
        }
        return "You already have an armor equipped!";
    }

    @Override
    public String getInfo(){
        return super.getInfo() + " - Defense: " + this.getValue();
    }

    @Override
    public Item duplicate() {
        return new Armor(this.getID(), this.getName(), this.getValue());
    }

}
