package br.ufjf.b4hive.model.inventory;

public class Effect {

    final int id;
    float multiplier;
    int value;
    int duration;

    public Effect(int id, float multiplier, int duration) {
        this.id = id;
        this.multiplier = multiplier;
        this.duration = duration;
    }

}
