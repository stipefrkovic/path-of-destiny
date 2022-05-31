package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.io.Serializable;

/**
 * Defines some attributes and functions that every NPC in the game has.
 * @author Jonas Scholz
 */
public abstract class NPC extends Entity implements Serializable {

    private String type;

    public String getType() {
        return type;
    }

    public abstract String takeActions(Player player, Scene currentScene, Action action);

    public String getFullName(){
        return "The " + getType() +" '"+ getName() + "' ";
    }

}
