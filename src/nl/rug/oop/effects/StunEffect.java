package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class StunEffect implements Effect {
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.setStunnedChance(0.3);
        }
    }

    @Override
    public String getEffectAdjective() {
        return "stunned";
    }

    @Override
    public String getName() {
        return "Stun";
    }
}