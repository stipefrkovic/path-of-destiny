package nl.rug.oop.player;

import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.List;

/**
 * Abstract class that defines the basic features of the player
 * @author Joni Baarda
 */
public abstract class Player extends Entity {

    private int gold;

    public int getGold() {
        return gold;
    }


    //abstract class
    public abstract List<Action> getFightActions();

    //non-abstract class
    public abstract List<String> getInventory();

    //The String is supposed to say what the player did
    //abstract class
    public abstract String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    //non-abstract class
    public abstract void addKill(String type);
}
