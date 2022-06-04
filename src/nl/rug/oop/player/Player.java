package nl.rug.oop.player;

import nl.rug.oop.items.Item;
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

    //Do NPCs also have gold?



    //Just because I put abstract methods in here does not mean that they have to be abstract
    public abstract List<Action> getFightActions();

    //non-abstract class
    public abstract List<String> getInventory();

    public abstract List<Item> getInventoryItems();

    //The String is supposed to say what the player did
    public abstract String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    //non-abstract class
    public abstract void addKill(String type);

    public abstract String useItem(String itemName);

    //The String is supposed to return what actually happend, so for example: You gained 12 Gold and 3 Healing potions.
    public abstract String addLoot(int gold, List<Item> items);
}
