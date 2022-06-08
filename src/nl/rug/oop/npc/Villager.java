package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;

import java.util.ArrayList;

public class Villager extends TalkingNPC{
    public Villager(String name, Dialogue dialogue, ItemFactory factory) {
        super(name, "Villager", 5, 0, dialogue, 1, 4, factory.createRandomItems(3,5));
    }
}
