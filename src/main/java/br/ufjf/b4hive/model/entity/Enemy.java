package br.ufjf.b4hive.model.entity;

import br.ufjf.b4hive.model.inventory.Weapon;

public class Enemy extends Breakable {

    public Enemy(int id, String name) {
        super(id, name);
    }

    @Override
    public int atk() {
        if (this.getInventory().getHand() != null)
            if (this.getInventory().getHand() instanceof Weapon weapon)
                return 1 + weapon.getValue();
        return 1;
    }

    @Override
    public Enemy duplicate() {
        Enemy e = new Enemy(this.getID(), this.getName());
        e.setInventory(this.getInventory().duplicate());
        return e;
    }

    public Enemy duplicate(int level) {
        Enemy enemy = new Enemy(this.getID(), this.getName());
        enemy.setInventory(this.getInventory().duplicate());

        return enemy;
    }

}
