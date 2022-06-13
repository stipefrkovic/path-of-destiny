package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

/**
 * A simple villager that can talk, mainly a class for convenient calling of this type of NPC.
 * @author Jonas Scholz
 */
public class Villager extends TalkingNPC{

    /**
     * Creates a villager according to the parameters with a health of 5 and strength of 0.
     * @param name The name of the villager.
     * @param dialogue The dialogue through which the villager can talk with the player.
     * @param factory The item factory for creating the loot of the villager.
     */
    public Villager(String name, Dialogue dialogue, ItemFactory factory) {
        super(name, "Villager", 5, 0, dialogue, 1, 4, factory.createRandomItems(3,5));
    }
}
