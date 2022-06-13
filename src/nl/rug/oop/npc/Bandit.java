package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

/**
 * A bandit npc, with a simple constructor to create NPC of this type easily.
 * @author Jonas Scholz
 */
public class Bandit extends StandardFighter{

    /**
     * Creates a Bandit.
     * @param name The name of the bandit.
     * @param factory An ItemFactory that generates the loot.
     */
    public Bandit(String name, ItemFactory factory) {
        super(name, "Bandit", 32, 4, 0, 1, factory.createRandomItems(0,1));
    }
}
