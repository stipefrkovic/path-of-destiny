package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

/**
 * An abstract class for all the possible effects in the game. It has all the functions necessary for executing
 * the effects on entities.
 * @author Andro Erdelez
 */
public abstract class Effect {
    /**
     * A variable which keeps track of how long the effect lasts.
     */
    protected int lifetime = 3;

    /**
     * Applies an appropriate effect on the given entity and updates its stats based on that effect.
     * @param entity Entity which is affected by the effect.
     */
    public abstract void update(Entity entity);

    /**
     * Creates an adjective corresponding to an appropriate effect which gets concatenated to an appropriate sentence.
     * @return Adjective of the effect in form of a string.
     */
    public abstract String getEffectAdjective();

    /**
     * Gets a name of an appropriate effect.
     * @return Name of the effect in form of a string.
     */
    public abstract String getName();
}