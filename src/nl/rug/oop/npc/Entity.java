package nl.rug.oop.npc;

import nl.rug.oop.effects.Effect;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class that is the super class of the player and NPCs, which allows for both of them being affected by Effects.
 * @author Jonas Scholz
 */
public abstract class Entity implements Serializable {

    protected String name;
    protected int health;
    protected int maxHealth;
    protected float strength;
    protected int gold;

    protected Entity(String name, int maxHealth, float strength){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
    }

    private ArrayList<Effect> effects = new ArrayList<>();
    private ArrayList<Effect> removeEffects = new ArrayList<>();


    protected void updateEffects(){
        for (Effect effect:effects) {
            effect.update(this);
        }
        effects.removeAll(removeEffects);
        removeEffects.clear();
    }

    public String addEffect(Effect effect){
        effects.add(effect);
        return getName() + " is " + effect.getEffectAdjective()+ ".";
    }

    public void removeEffect(Effect effect){
        removeEffects.add(effect);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public float getStrength() {
        return strength;
    }

    public int getGold() {
        return gold;
    }

    public String takeDamage(int damage){
        health -= damage;
        if(health<0){
            health = 0;
        }
        return this.getName()+" has lost "+ damage + " health.";
    }

    /**
     * Can be used to change the amount of gold an Entity has.
     * @param amount The amount by which the amount of gold is changed.
     * @return If the transaction was successful or if the change would have left the entity in the minus.
     */
    public boolean changeGoldAmount(int amount){
        if(gold+amount>=0){
            gold += amount;
            return true;
        }
        return false;
    }
}
