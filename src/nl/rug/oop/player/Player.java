package nl.rug.oop.player;

import nl.rug.oop.effects.Effect;
import nl.rug.oop.items.Item;
import nl.rug.oop.npc.Entity;
import nl.rug.oop.npc.NPC;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class that defines the basic features of the player
 * @author Joni Baarda
 */
public abstract class Player extends Entity implements Serializable {
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
        return killCounter.get(type);
    }

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

    public HashMap<String, Integer> getInventoryCount() {
        HashMap<String, Integer> inventoryCount = new HashMap<>();
        List<String> differentItems = new ArrayList<>();
        for (Item item : inventoryItems) {
            if(!differentItems.contains(item.getItemAdjective())){
                differentItems.add(item.getItemAdjective());
                inventoryCount.put(item.getItemAdjective(), 0);
            }
        }
        for (Item item : inventoryItems) {
            inventoryCount.put(item.getItemAdjective(), inventoryCount.get(item.getItemAdjective()) + 1);
        }
        return inventoryCount;
    }

    public abstract int getEnergy();

    public abstract void setEnergy(int energy);
    public void update(){
        //TODO:implement update method
    }

    public abstract List<Action> getFightActions();

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
    public String removeSpecifiedItems(List<Item> itemsToRemove){
        StringBuilder StringItemsToRemove = new StringBuilder();
        for (Item item:itemsToRemove) {
            inventoryItems.remove(item);
            StringItemsToRemove.append(item.getItemAdjective()).append(", ");
        }
        String temp = StringItemsToRemove.toString();
        return "You lost " + (temp.substring(0, temp.length()-2)) + ".";
    }


    public String useItem(String itemName){
        for (Item item:inventoryItems) {
            if(Objects.equals(item.getItemAdjective(), itemName)){
                inventoryItems.remove(item);
                switch (itemName) {
                    case "effect":
                        //removes effect based on user's choice
                        return "You used an effect remove potion and removed " + "effect" + ".";
                    case "health":
                        health = Math.min(health + 10, maxHealth);
                        return "You used a health potion and gained 10 health";
                    default:
                        return consumeAppropriately();
                }
            }
        }
        return null;
    }

    public abstract String consumeAppropriately();

    public String addLoot(int gold, List<Item> items){
        int healthPotionCount = 0; int manaPotionCount = 0; int staminaPotionCount = 0; int removeEffectPotionCount = 0;
        this.gold += gold;
        for (Item item:items) {
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
        StringBuilder addedItems = new StringBuilder();

        if(healthPotionCount > 0){
            addedItems.append(" and ").append(healthPotionCount).append(" health potions");
        }
        if(manaPotionCount > 0){
            addedItems.append(" and ").append(manaPotionCount).append(" mana potions");
        }
        if(staminaPotionCount > 0){
            addedItems.append(" and ").append(staminaPotionCount).append(" stamina potions");
        }
        if(removeEffectPotionCount > 0){
            addedItems.append(" and ").append(removeEffectPotionCount).append(" remove effect potions");
        }
        addedItems.append(".");
        String stringAddedItems = addedItems.toString();
        if(gold>=0) {
            return "You gained " + gold + stringAddedItems;
        }else{
            return "You lost " + gold + "and gained " + stringAddedItems.substring(3);
        }
    }
}
