package br.ufjf.b4hive.model.entity;
import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.inventory.Effect;
import br.ufjf.b4hive.model.inventory.Inventory;

public abstract class Entity {

    private final int id;
	private final String name;
	private float hpCurrent;
	private Coordinate position;
	private Inventory inventory;
	private List<Effect> effects;

	public Entity(int id, String name) {
		this.id = id;
		this.name = name;
		this.hpCurrent = 1;
		this.position = null;
		this.inventory = new Inventory();
		this.effects = new ArrayList<>();
	}

	public int getID(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public int getMaxHP(){
		return 100; // TODO: add the HP modifiers like armor
	}
	public int getCurrentHP(){
		return getMaxHP() * (int) this.hpCurrent;
	}
	public Coordinate getPosition(){
		return this.position;
	}
	public void setPosition(Coordinate position){
		this.position = position;
	}

}
