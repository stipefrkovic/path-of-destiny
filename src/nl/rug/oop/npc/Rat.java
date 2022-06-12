package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

/**
 * Rat NPC, mainly a class for convenient calling of this type of NPC..
 * @author Jonas Scholz
 */
public class Rat extends StandardFighter{

    /**
     * Creates a new instance of the rat npc.
     * @param name The name of the rat.
     * @param factory An itemFactory that is used to generate the loot.
     */
    public Rat(String name, ItemFactory factory) {
        super(name, "Rat", 15, 3, 0, 1, factory.createRandomItems(0));
    }
}
