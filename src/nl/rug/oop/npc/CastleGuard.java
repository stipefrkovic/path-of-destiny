package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

/**
 * A Castle Guard, a class used for convenient NPC creation
 * @author Jonas Scholz
 */
public class CastleGuard extends Guard{

    /**
     * Creates a new CastleGuard.
     * @param name The name of the NPC.
     * @param factory An ItemFactory that is used to generate the loot of this npc.
     */
    public CastleGuard(String name, ItemFactory factory) {
        super(name, "Castle Guard", 50, 6, 0, 3, factory.createRandomItems(0,2), 6);
    }
}
