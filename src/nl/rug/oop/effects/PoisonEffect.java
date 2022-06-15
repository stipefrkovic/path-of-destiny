package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 * A class that implements a poison effect.
 * @author Andro Erdelez
 */
public class PoisonEffect extends Effect {
    /**
     * Applies the poison effect for three turns.
     * @param entity Entity which is affected by the effect.
     */
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.changeHealth(5);
        }
        lifetime--;
    }

    /**
     * Creates an adjective appropriate to this effect which will later be appended to a sentence.
     * @return Adjective which corresponds to the poison effect.
     */
    @Override
    public String getEffectAdjective() {
        return "poisoned";
    }

    /**
     * Gets the name of the poison effect.
     * @return Name of the poison effect.
     */
    @Override
    public String getName() {
        return "Poison";
    }
}