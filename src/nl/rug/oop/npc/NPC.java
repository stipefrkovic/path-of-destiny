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

    protected NPC(String name, String type, int maxHealth, float strength, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, maxHealth, strength);
        this.gold = (int) Math.round((Math.random()*(maxGold-minGold))+minGold);
        this.loot = lootItems;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public final String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction){
        if(isStunned()){
            return this.getName() + " is stunned. ";
        }
        if(isFightAction){
            this.updateEffects();
            return takeFightActions(player, currentScene, action);
        }else{
            return takeNonFightActions(player, currentScene, action);
        }
    }

    protected abstract String takeNonFightActions(Player player, Scene currentScene, Action action);

    protected String takeFightActions(Player player, Scene currentScene, Action action){
        if(strength==0){
            return this.getName() + " cowers in fear. ";
        }
        int damage = (int) Math.round(strength*(Math.random()*0.1+0.95));
        return doDamage(player, damage, currentScene);
    }

    protected final String doDamage(Player player, int damage, Scene currentScene){
        NPCScene npcScene = (NPCScene) currentScene;
        String description = isConfused(damage, npcScene.getNPCs());
        if(description.equals("")){
            description = player.takeDamage(this, damage);
        }
        return description;
    }

    @Override
    public String getName(){
        return "The " + getType() +" '"+ super.getName() + "' ";
    }

    public List<Item> getLoot(){
        return new ArrayList<>(loot);
    }

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
