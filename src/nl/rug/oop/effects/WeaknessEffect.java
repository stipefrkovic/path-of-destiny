package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 * A class that implements a weakness effect.
 * @author Andro Erdelez
 */
public class WeaknessEffect extends Effect {
    /**
     * Applies the weakness effect for three turns.
     * @param entity Entity which is affected by the effect.
     */
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.changeStrengthTemporary(0.5F);
        }
        lifetime--;
    }

    /**
     * Creates an adjective appropriate to this effect which will later be appended to a sentence.
     * @return Adjective which corresponds to the weakness effect.
     */
    @Override
    public String getEffectAdjective() {
        return "weakened";
    }

    /**
     * Gets the name of the weakness effect.
     * @return Name of the weakness effect.
     */
    @Override
    public String getName() {
        return "Weakness";
    }
}