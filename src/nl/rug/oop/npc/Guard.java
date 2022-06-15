package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

/**
 * A guard NPC, with somewhat special attack behaviour.
 * @author Jonas Scholz
 */
public class Guard extends StandardFighter{

    private int villagerThreshold;

    /**
     * Create a Guard NPC and initialize all attributes.
     * @param name The name of the NPC.
     * @param type The type of the NPC.
     * @param maxHealth The maximum amount of health.
     * @param strength The strength of the npc.
     * @param minGold The minimal amount of gold (inclusive).
     * @param maxGold The maximum amount of gold (exclusive).
     * @param lootItems The list of items that the NPC will drop.
     * @param villagerThreshold The threshold of villagers killed before the guards gets stronger.
     */
    public Guard(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems, int villagerThreshold) {
        super(name, type, maxHealth, strength, minGold, maxGold, lootItems);
        this.villagerThreshold = villagerThreshold;
    }

    /**
     * Creates a guard npc with determined stats.
     * @param name The name of the NPC.
     * @param factory An ItemFactory that generates the loot.
     */
    public Guard(String name, ItemFactory factory) {
        this(name, "Guard", 25, 5, 0, 2, factory.createRandomItems(0,1), 3);
    }

    /**
     * Buffs the strength of the guard when the threshold of villagers were killed and attacks the player.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return A description of what the guard did.
     */
    @Override
    protected String takeFightActions(Player player, Scene currentScene, Action action) {
        if(player.getKillsForType("Villager")>=villagerThreshold){
            this.changeStrengthTemporary(1.5f);
        }
        return super.takeFightActions(player, currentScene, action);
    }
}
