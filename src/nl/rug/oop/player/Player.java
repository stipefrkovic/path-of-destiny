package nl.rug.oop.player;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Abstract class that defines the basic features of the player
 * @author Joni Baarda
 */
public abstract class Player extends Entity implements Serializable {

    //Just because I put abstract methods in here does not mean that they have to be abstract
    public abstract List<Action> getFightActions();
    private HashMap<String, Integer> killCounter = new HashMap<>();
    private int totalKills = 0;
    List<Item> inventoryItems = new ArrayList<>();


    protected Player(String name, int health, int maxHealth, float strength, int gold) {
        super(name, maxHealth, strength);
        this.health = health;
        this.gold = gold;
    }

    public HashMap<String, Integer> getKillCounter() {
        return killCounter;
    }

    public int getTotalKills() {
        return totalKills;
    }
    

    public int getKillsForType(String type){
        //TODO: Implement this
        return 0;
    }

    //non-abstract class

    public List<String> getInventory(){
        List<String> inventory = new ArrayList<>();
        for (Item item:inventoryItems) {
            inventory.add(item.getItemAdjective());
        }
        return inventory;
    }

    public List<Item> getInventoryItems(){
        return inventoryItems;
    }

    //The String is supposed to say what the player did
    public abstract String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    public void addKill(String type){
        if(killCounter.containsKey(type)){
            killCounter.put(type, killCounter.get(type) + 1);
        } else{
            killCounter.put(type, 1);
        }
        totalKills += 1;
    }


    //The String is supposed to return what actually happened, so for example: You lost 3 Healing potions and 2 Mana potions.
    //done ish
    public String removeSpecifiedItems(List<Item> itemsToRemove){
        StringBuilder StringItemsToRemove = new StringBuilder();
        //int counter = 0;
        //probably do the other for statement to be able to compare items
        for (Item item:itemsToRemove) {
            inventoryItems.remove(item);
            StringItemsToRemove.append(item.getItemAdjective()).append(", ");
        }
        String temp = StringItemsToRemove.toString();
        return "You lost " + (temp.substring(0, temp.length()-2)) + ".";
    }

    //public abstract String useItem(String itemName);
    public String useItem(String itemName){
        switch (itemName) {
            case "effect":
                //removes effect based on user's choice
                //inventoryItems.remove();
                return "You used an effect remove potion and removed " + "effect" + ".";
            case "health":
                health = Math.min(health + 10, maxHealth);
                //inventoryItems.remove();
                return "You used a health potion and gained 10 health";
            default:
                //inventoryItems.remove(itemName);
                return consumeAppropriately();
        }

    }

    public abstract String consumeAppropriately();

    //The String is supposed to return what actually happened, so for example: You gained 12 Gold and 3 Healing potions.
    //The amount of gold might be negative, so then the message should change
    //sort the lists
    public String addLoot(int gold, List<Item> items){
        int healthPotionCount = 0; int manaPotionCount = 0; int staminaPotionCount = 0; int removeEffectPotionCount = 0;
        this.gold += gold;
        StringBuilder stringOfItems = new StringBuilder();
        /*for (Item item:items) {
            switch (item.getItemAdjective()) {
                case "health potion" -> healthPotionCount += 1;
                case "mana potion" -> manaPotionCount += 1;
                case "stamina potion" -> staminaPotionCount += 1;
                case "potion removes an effect" -> removeEffectPotionCount += 1;
                default -> {
                }
            }
            inventoryItems.add(item);
        }
        if(gold>=0) {
            return "You gained " + gold + " and " + healthPotionCount + " health potions and " + manaPotionCount + " mana potions and " + staminaPotionCount + " stamina potions and " + removeEffectPotionCount + " remove effects potions.";
        }else{
            return "You lost " + gold + " and gained " + healthPotionCount + " health potions and " + manaPotionCount + " mana potions and " + staminaPotionCount + " stamina potions and " + removeEffectPotionCount + " remove effects potions.";
        }*/
        for (Item item:items) {
            inventoryItems.add(item);
            stringOfItems.append(item.getItemAdjective()).append(", ");
        }
        String temp = stringOfItems.toString();
        if(gold>=0) {
            return "You gained " + gold + " and " + temp + ".";
        }else{
            return "You lost " + gold + " and gained " + temp + ".";
        }
    }
}
