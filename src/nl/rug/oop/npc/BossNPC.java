package nl.rug.oop.npc;

import nl.rug.oop.dialogue.Dialogue;
import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;

/**
 * An abstract template for the bosses, which allows for special actions when the boss reaches 75%, 50% and 25% health.
 * @author Jonas Scholz
 */
public abstract class BossNPC extends TalkingNPC{

    private boolean healthBelow75 = false;
    private boolean healthBelow50 = false;
    private boolean healthBelow25 = false;

    /**
     * Calls the super constructor.
     * @param name The name of the NPC.
     * @param type The type of the NPC, for example: Spider.
     * @param maxHealth The maximum health of the NPC.
     * @param strength The strength of the NPC.
     * @param dialogue The dialogue that the NPC can have with the player.
     * @param minGold The minimal amount of gold this NPC will have (inclusive).
     * @param maxGold The maximal amount of gold this NPC will have (exclusive).
     * @param lootItems The list of items that the npc will drop on defeat.
     */
    public BossNPC(String name, String type, int maxHealth, int strength, Dialogue dialogue, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, dialogue, minGold, maxGold, lootItems);
    }

    /**
     * Takes a special action the first time it is below 75%, 50% and 25% health otherwise performs actions as defined in the parent class.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return A description of what the npc boss did.
     */
    @Override
    protected String takeFightActions(Player player, Scene currentScene, Action action) {
        String description;
        if(this.health<0.75*this.maxHealth && !healthBelow75){
            description = onFirstBelow75Health(player, currentScene, action);
            healthBelow75 = true;
        }else if(this.health<0.5*this.maxHealth && !healthBelow50){
            description = onFirstBelow50Health(player, currentScene, action);
            healthBelow50 = true;
        }else if(this.health<0.25*this.maxHealth && !healthBelow25){
            description = onFirstBelow25Health(player, currentScene, action);
            healthBelow25 = true;
        }else{
            description = super.takeFightActions(player, currentScene, action);
        }
        return description;
    }

    /**
     * Returns the name of the boss in the format: [name] the [type]
     * @return The name of the boss in the format: [name] the [type]
     */
    @Override
    public String getName() {
        return name + " the " + getType();
    }

    /**
     * Gets called when the boss is the first time below 75% health.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return A description of what the boss did.
     */
    protected abstract String onFirstBelow75Health(Player player, Scene currentScene, Action action);

    /**
     * Gets called when the boss is the first time below 50% health.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return A description of what the boss did.
     */
    protected abstract String onFirstBelow50Health(Player player, Scene currentScene, Action action);

    /**
     * Gets called when the boss is the first time below 25% health.
     * @param player The player that plays the game.
     * @param currentScene The current scene that the boss is in.
     * @param action The action that the user took.
     * @return A description of what the boss did.
     */
    protected abstract String onFirstBelow25Health(Player player, Scene currentScene, Action action);
}
