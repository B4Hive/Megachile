package br.ufjf.b4hive.model.entity;
import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.inventory.Effect;
import br.ufjf.b4hive.model.inventory.Inventory;

public abstract class Entity {
    private final int id;
	private final String name;
	private int hpBase;
	private float hpCurrent;
	private Coordinate position;
	private Inventory inventory;
	private List<Effect> effects;

	public Entity(int id, String name) {
		this.id = id;
		this.name = name;
		this.hpBase = 0;
		this.hpCurrent = 0;
		this.position = null;
		this.inventory = new Inventory();
		this.effects = new ArrayList<>();
	}
}
