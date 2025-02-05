package br.ufjf.b4hive.model.entity;

public class Bee extends Entity{ 
    public Bee(int id, String name) {
        super(id, name);
    }
    
    @Override
    public int atk(){
        return 1;//add the atk modifiers like weapon
    }
}
