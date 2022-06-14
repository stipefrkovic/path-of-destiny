package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class ConfusionEffect implements Effect {
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.setConfusionChance(0.3);
        }
    }

    @Override
    public String getEffectAdjective() {
        return "confused";
    }

    @Override
    public String getName() {
        return "Confusion";
    }
}