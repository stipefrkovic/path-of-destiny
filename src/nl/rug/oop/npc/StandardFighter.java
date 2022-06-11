package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

public class StandardFighter extends NPC{

    public StandardFighter(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
    }

    @Override
    protected String takeNonFightActions(Player player, Scene currentScene, Action action) {
        return "";
    }
}
