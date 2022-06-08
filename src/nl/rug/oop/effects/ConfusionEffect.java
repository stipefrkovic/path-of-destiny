package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class ConfusionEffect implements Effect {
    @Override
    public void update(Entity entity) {
        entity.executeEffect("confusion");
    }

    @Override
    public String getEffectAdjective() {
        return "confused";
    }
}
