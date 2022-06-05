package nl.rug.oop.effects;

import nl.rug.oop.npc.Entity;

public abstract class Effect {

    public abstract void update(Entity entity);

    //This should give back: poisoned, stunned, etc. depending on what it does.
    public abstract String getEffectAdjective();
}
