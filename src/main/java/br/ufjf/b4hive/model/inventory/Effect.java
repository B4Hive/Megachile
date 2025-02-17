package br.ufjf.b4hive.model.inventory;

public class Effect {

    final int id;
    final String name;
    final float multiplier;
    int value;
    int duration;

    public Effect(int id, String name, float multiplier, int duration) {
        this.id = id;
        this.name = name;
        this.multiplier = multiplier;
        this.duration = duration;
    }

    public int getID() {
        return this.id;
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

    public void setValue(int value) {
        this.value = value;
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
        return "Effect: " + this.name //won't be ID
                + "; Multiplier: " + this.multiplier + "; Duration: " + this.duration;
    }
}
