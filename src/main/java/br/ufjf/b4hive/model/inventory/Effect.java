package br.ufjf.b4hive.model.inventory;

import br.ufjf.b4hive.model.entity.Entity;

public class Effect {

    private final int id;
    private final String name;
    private final float multiplier;
    private int value;
    private int duration;

    public Effect(int id, String name, float multiplier, int duration) {
        this.id = id;
        this.name = name;
        this.multiplier = multiplier;
        this.duration = duration;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float getMultiplier() {
        return this.multiplier;
    }

    public int getValue() {
        return this.value;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setValue(int status) {
        this.value = (int) (this.multiplier * status);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void tick() {
        this.duration--;
    }

    public boolean isOver() {
        return this.duration <= 0;
    }

    public String getInfo() {
        return "Effect: [" + this.name
                + "; Multiplier: " + this.multiplier + "; Duration: " + this.duration + "]";
    }

    public String apply(Entity target) {
        int i = this.getID();
        String temp = null;
        if (i == 51) {
            temp = target.takeDamage(value);
            this.tick();
        }
        if (i == 52) {
            temp = target.takeDamage(-value);
            this.tick();
        }
        return temp;
    }

    public Effect duplicate() {
        return new Effect(this.id, this.name, this.multiplier, this.duration);
    }

}
