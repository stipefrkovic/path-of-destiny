package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;


public class CastleGuard extends Guard{
    public CastleGuard(String name, ItemFactory factory) {
        super(name, "Castle Guard", 50, 6, 0, 3, factory.createRandomItems(0,2));
    }
}
