package br.ufjf.b4hive.model.entity;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.inventory.Armor;
import br.ufjf.b4hive.model.inventory.Effect;
import br.ufjf.b4hive.model.inventory.Inventory;
import br.ufjf.b4hive.model.inventory.Item;
import br.ufjf.b4hive.model.inventory.Weapon;

public abstract class Entity {

    private final int id;
	private final String name;
	private float hpCurrent;
	private Coordinate position;
	private final Inventory inventory;
	private final List<Effect> effects;

	public Entity(int id, String name) {
		this.id = id;
		this.name = name;
		this.hpCurrent = 1;
		this.position = null;
		this.inventory = new Inventory();
		this.effects = new ArrayList<>();
	}

	public Entity(int id, String name, Weapon weapon, Armor armor) {
		this.id = id;
		this.name = name;
		this.hpCurrent = 1;
		this.position = null;
		this.inventory = new Inventory();
		this.inventory.holdItem(weapon);
		this.inventory.setBody(armor);
		this.effects = new ArrayList<>();
	}

	public int getID(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}

	public int getMaxHP(){
		if (this.inventory.getBody() instanceof Armor armor)
			return 10 + armor.getValue();
		else
			return 10;
	}

	public int getCurrentHP(){
		return getMaxHP() * (int) this.hpCurrent;
	}

	public Coordinate getPosition(){
		return this.position;
	}

	public String getInfo(){
		return this.name + " - HP: " + getCurrentHP() + "/" + getMaxHP();
	}

	public void setPosition(Coordinate position){
		this.position = position;
	}

	public Inventory getInventory(){
		return this.inventory;
	}

	public List<Effect> getEffects(){
		return this.effects;
	}

	public String addEffect(Effect effect){
		this.effects.add(effect);
		return this.name + " got affected by " + effect.getID() + ".";
	}

	public int atk(){
		int a = 1;
		if (this.inventory.getHand() instanceof Weapon weapon){
			a += weapon.getValue();
		}
		return a;
	}

	public String takeDamage(int amount){
		String result = this.name + " took " + amount + " damage. ";
		float hp = getMaxHP() * this.hpCurrent;
		hp -= amount;
		this.hpCurrent = hp / getMaxHP();
		int hpPercent = (int) (this.hpCurrent * 100);
		result += hpPercent + "% HP remaining.";
		if(hpPercent < 0) {
			this.hpCurrent = 0;
		}
		return result;
	}

	public boolean alive(){
		return this.hpCurrent > 0;
	}

	public Item drop(int index){
		if(index < 50)
	        return new Item(30, "Item");
		else
			return new Item(31, "Meti");
    }

}
