package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public class WeaknessEffect implements Effect {
    @Override
    public void update(Entity entity) {
        entity.executeEffect("weakness");
    }

    @Override
    public String getEffectAdjective() {
        return "weakened";
    }
}
