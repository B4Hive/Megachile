package br.ufjf.b4hive.model.inventory;

public class Ability {

    private final int cooldownMax;
    private int cooldown;
    private final Effect effect;

    public Ability(int cooldownMax, Effect effect) {
        this.cooldownMax = cooldownMax;
        this.cooldown = cooldownMax;
        this.effect = effect;
    }

    public int getCooldownMax() {
        return cooldownMax;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void resetCooldown() {
        this.cooldown = cooldownMax;
    }

    public void lowerCooldown() {
        this.cooldown--;
    }

    public Effect getEffect() {
        return effect;
    }

    public String getInfo(){
        return "Cooldown: " + this.cooldown + "/" + this.cooldownMax + " - "; // + effect.getInfo();
    }

    
}
