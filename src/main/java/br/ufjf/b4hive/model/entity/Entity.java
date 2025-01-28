package br.ufjf.b4hive.model.entity;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.inventory.Inventory;
import java.util.ArrayList;

public abstract class Entity {
    private final int id;
	private final String name;
	private int hpBase;
	private float hpCurrent;
	privte Cooordinate position;
	private Inventory inventory;
	private List<Effect> effects;
}
