package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

/**
 * Simple NPC fighter class that implements the takeNonFightActions to avoid code duplication.
 * @author  Jonas Scholz
 */
public class StandardFighter extends NPC{

    /**
     * Calls the constructor of the parent with the given parameters.
     * @param name The name of the NPC.
     * @param type The type of the NPC.
     * @param maxHealth The maximum amount of health.
     * @param strength The strength of the npc.
     * @param minGold The minimal amount of gold (inclusive).
     * @param maxGold The maximum amount of gold (exclusive).
     * @param lootItems The list of items that the NPC will drop.
     */
    public StandardFighter(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
    }

    /**
     * Returns an empty string, as a standard fighter does not have any non fighting related actions.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return An empty String.
     */
    @Override
    protected String takeNonFightActions(Player player, Scene currentScene, Action action) {
        return "";
    }
}
