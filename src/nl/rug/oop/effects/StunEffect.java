package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 * A class that implements a stun effect.
 * @author Andro Erdelez
 */
public class StunEffect extends Effect {
    /**
     * Applies the stun effect based on the chance of 30%. The effect is applied for three turns.
     * @param entity Entity which is affected by the effect.
     */
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.setStunnedChance(0.3);
        }
        lifetime--;
    }

    /**
     * Creates an adjective appropriate to this effect which will later be appended to a sentence.
     * @return Adjective which corresponds to the stun effect.
     */
    @Override
    public String getEffectAdjective() {
        return "stunned";
    }

    /**
     * Gets the name of the stun effect.
     * @return Name of the stun effect.
     */
    @Override
    public String getName() {
        return "Stun";
    }
}