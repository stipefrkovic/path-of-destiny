package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.NPCScene;
import nl.rug.oop.scene.Scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines some attributes and functions that every NPC in the game has.
 * @author Jonas Scholz
 */
public abstract class NPC extends Entity implements Serializable, Cloneable {

    private String type;
    protected List<Item> loot;

    /**
     * Intializes all attributes.
     * @param name The name of the NPC.
     * @param type The type of the NPC, for example: Spider.
     * @param maxHealth The maximum health of the NPC.
     * @param strength The strength of the NPC.
     * @param minGold The minimal amount of gold this NPC will have (inclusive).
     * @param maxGold The maximal amount of gold this NPC will have (exclusive).
     * @param lootItems The list of items that the npc will drop on defeat.
     */
    protected NPC(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, maxHealth, strength);
        this.gold = (int) Math.round((Math.random()*(maxGold-minGold))+minGold);
        this.loot = lootItems;
        this.type = type;
    }

    /**
     * Returns the type of the NPC.
     * @return The type of the NPC.
     */
    public String getType() {
        return type;
    }

    /**
     * Takes actions based on if the NPC is in a fight or not, furthermore makes sure that stunning is working as intended.
     * This function can not be overriden, instead either takeNonFightActions or takeFightActions should be overridden.
     * @param player The player that plays the game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @param isFight If the NPC is in a fight with the player.
     * @return A description of what the NPC did.
     */
    public final String takeActions(Player player, Scene currentScene, Action action, boolean isFight){
        if(isStunned()){
            return this.getName() + " is stunned. ";
        }
        if(isFight){
            this.updateEffects();
            return takeFightActions(player, currentScene, action);
        }else{
            return takeNonFightActions(player, currentScene, action);
        }
    }

    /**
     * Takes actions when no fight is happening.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return A description of what the NPC did.
     */
    protected abstract String takeNonFightActions(Player player, Scene currentScene, Action action);

    /**
     * Takes actions when a fight is happening.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return A description of what the NPC did.
     */
    protected String takeFightActions(Player player, Scene currentScene, Action action){
        if(strength==0){
            return this.getName() + " cowers in fear. ";
        }
        int damage = (int) Math.round(strength*(Math.random()*0.1+0.95));
        return doDamage(player, damage, currentScene);
    }

    /**
     * Deals damage to the player unless the NPC is confused.
     * Should always be used to deal damage, as it makes sure that confusion effects work.
     * @param player The player that plays that game.
     * @param damage The amount of damage the NPC is going to deal.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @return A description of what the NPC did.
     */
    protected final String doDamage(Player player, int damage, Scene currentScene){
        NPCScene npcScene = (NPCScene) currentScene;
        String description = isConfused(damage, npcScene.getNPCs());
        if(description.equals("")){
            description = player.takeDamage(this, damage);
        }
        return description;
    }

    /**
     * Returns the name and type of the npc in the format: The [type] '[name]'
     * @return The name and type of the npc in the format: The [type] '[name]'
     */
    @Override
    public String getName(){
        return "The " + getType() +" '"+ super.getName() + "' ";
    }

    /**
     * Returns the items that the NPC drops on being defeated.
     * @return The list of items that the NPC drops on being defeated.
     */
    public List<Item> getLoot(){
        return new ArrayList<>(loot);
    }

    /**
     * Clones the NPC
     * @return A cloned object of this NPC object.
     */
    @Override
    public NPC clone(){
        try {
            return (NPC) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
