package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

public class Guard extends StandardFighter{
    public Guard(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
    }

    public Guard(String name, ItemFactory factory) {
        this(name, "Guard", 25, 5, 0, 2, factory.createRandomItems(0,1));
    }

    @Override
    protected String attack(Player player, Scene currentScene, Action action) {
        if(player.getKillsForType("Villager")>=3){
            strength *= 1.5;
        }
        String attackString = super.attack(player, currentScene, action);
        if(player.getKillsForType("Villager")>=3){
            strength /= 1.5;
        }
        return attackString;
    }
}
