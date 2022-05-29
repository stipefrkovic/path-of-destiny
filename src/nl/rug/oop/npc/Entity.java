package nl.rug.oop.npc;

import nl.rug.oop.effects.Effect;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class that is the super class of the player and NPCs, which allows for both of them being affected by Effects.
 */
public abstract class Entity implements Serializable {

    private String name;
    private int health;
    private int maxHealth;
    private float strength;
    private ArrayList<Effect> effects = new ArrayList<>();
    private ArrayList<Effect> removeEffects = new ArrayList<>();


    protected void updateEffects(){
        for (Effect effect:effects) {
            effect.update(this);
        }
        effects.removeAll(removeEffects);
        removeEffects.clear();
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
}
