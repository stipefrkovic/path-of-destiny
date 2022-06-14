package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class WeaknessEffect implements Effect {
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.changeStrengthTemporary(0.5F);
        }
    }

    @Override
    public String getEffectAdjective() {
        return "weakened";
    }

    @Override
    public String getName() {
        return "Weakness";
    }
}