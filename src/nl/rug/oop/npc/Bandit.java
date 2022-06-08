package nl.rug.oop.npc;

import nl.rug.oop.items.ItemFactory;

public class Bandit extends StandardFighter{

    public Bandit(String name, ItemFactory factory) {
        super(name, "Bandit", 32, 4, 0, 1, factory.createRandomItems(0,1));
    }
}
