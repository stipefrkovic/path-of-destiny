package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class PoisonEffect implements Effect{
    @Override
    public void update(Entity entity) {
        entity.executeEffect("poison");
    }

    @Override
    public String getEffectAdjective() {
        return "poisoned";
    }
}
