package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 *
 * @author Andro Erdelez
 */
public interface Effect {
    int lifetime = 3;
    void update(Entity entity);

    //This should give back:     poisoned, stunned, etc. depending on what it does.
    String getEffectAdjective();

    String getName();
}