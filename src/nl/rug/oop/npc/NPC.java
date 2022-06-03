package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines some attributes and functions that every NPC in the game has.
 * @author Jonas Scholz
 */
public abstract class NPC extends Entity implements Serializable, Cloneable {

    protected String type;
    protected List<Item> loot = new ArrayList<>();

    public String getType() {
        return type;
    }

    public abstract String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction);

    public String getFullName(){
        return "The " + getType() +" '"+ getName() + "' ";
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
