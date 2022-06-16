package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 * A class that implements a confusion effect.
 * @author Andro Erdelez
 */
public class ConfusionEffect extends Effect {
    /**
     * Applies the confusion effect based on the chance of 30%. The effect is applied for three turns.
     * @param entity Entity which is affected by the effect.
     */
    @Override
    public void update(Entity entity) {
        if(lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.setConfusionChance(0.3);
        }
        lifetime--;
    }

    /**
     * Creates an adjective appropriate to this effect which will later be appended to a sentence.
     * @return Adjective which corresponds to the confusion effect.
     */
    @Override
    public String getEffectAdjective() {
        return "confused";
    }

    /**
     * Gets the name of the confusion effect.
     * @return Name of the confusion effect.
     */
    @Override
    public String getName() {
        return "Confusion";
    }
}