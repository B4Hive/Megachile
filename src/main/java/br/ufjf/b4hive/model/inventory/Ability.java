package br.ufjf.b4hive.model.inventory;

public class Ability {

    private final int cooldownMax;
    private int cooldown;
    private final Effect effect;

    public Ability(int cooldownMax, Effect effect) {
        this.cooldownMax = cooldownMax;
        this.cooldown = 0;
        this.effect = effect;
    }

    public int getCooldownMax() {
        return this.cooldownMax;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public void resetCooldown() {
        this.cooldown = this.cooldownMax;
    }

    public void tickCooldown() {
        if (this.cooldown > 0)
            this.cooldown--;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public String getInfo() {
        return effect.getInfo() + " - Cooldown: " + this.cooldown + "/" + this.cooldownMax;
    }

    public Effect use(int status) {
        if (this.cooldown <= 0) {
            this.resetCooldown();
            Effect e = this.effect.duplicate();
            e.setValue(status);
            return e;
        }
        return null;
    }

}
