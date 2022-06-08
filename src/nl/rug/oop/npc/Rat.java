package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

public class Rat extends StandardFighter{

    public Rat(String name, ItemFactory factory) {
        super(name, "Rat", 15, 3, 0, 1, factory.createRandomItems(0));
    }
}
