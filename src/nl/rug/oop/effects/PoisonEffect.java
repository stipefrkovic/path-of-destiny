package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class PoisonEffect implements Effect{
    @Override
    public void update(Entity entity) {
        if (lifetime == 0) {
            entity.removeEffect(this);
        } else {
            entity.changeHealth(5);
        }
    }

    @Override
    public String getEffectAdjective() {
        return "poisoned";
    }

    @Override
    public String getName() {
        return "Poison";
    }
}