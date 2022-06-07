package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that defines the basic features of the player
 * @author Joni Baarda
 */
public abstract class Player extends Entity {

    //Just because I put abstract methods in here does not mean that they have to be abstract
    public abstract List<Action> getFightActions();

    //non-abstract class
    public List<String> getInventory(){
        List<String> inventory = new ArrayList<>();
        //get the inventory somehow? Probably dependent on the actual inventory?
        return inventory;
    }

    public List<Item> getInventoryItems(){

        return null;
    }

    //The String is supposed to say what the player did
    public abstract String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    public void addKill(String type){
        //use counters to add the kills?
        //add kill to type kill? Maybe with if statement? Hashmap or library?
        //if type is friendly: add to friendly kill / or set friendly types
        //if type is evil: add to enemy kill / or set enemy types
    }


    //The String is supposed to return what actually happened, so for example: You lost 3 Healing potions and 2 Mana potions.
    //done ish
    public String removeSpecifiedItems(List<Item> itemsToRemove){
        StringBuilder StringItemsToRemove = new StringBuilder();
        for (Item item:itemsToRemove) {
            StringItemsToRemove.append(item.toString()).append(", ");
        }
        String temp = StringItemsToRemove.toString();
        return "You lost " + (temp.substring(0, temp.length()-2)) + ".";
    }

    public String useItem(String itemName){
        //if useItem is called, pass the item on to the inventory to delete it
        return null;
    }

    //The String is supposed to return what actually happened, so for example: You gained 12 Gold and 3 Healing potions.
    //The amount of gold might be negative, so then the message should change
    public String addLoot(int gold, List<Item> items){
        StringBuilder stringOfItems = new StringBuilder();
        for (Item item:items) {
            stringOfItems.append(item.toString()).append(", ");
        }
        String temp = stringOfItems.toString();
        if(gold>=0) {
            return "You gained " + gold + " and " + temp + ".";
        }else{
            return "You lost " + gold + " and gained " + temp + ".";
        }
    }
}
