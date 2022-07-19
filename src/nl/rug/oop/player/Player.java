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

/**
 * Abstract class that defines the basic features of the player. Extends from the class Entity and is the superclass of Mage and Warrior
 * @author Joni Baarda
 */
public abstract class Player extends Entity implements Serializable {
    private HashMap<String, Integer> killCounter = new HashMap<>();
    List<Item> inventoryItems = new ArrayList<>();


    /**
     * creates the player
     * @param name the name of the player
     * @param health the start health of the player
     * @param maxHealth the start max health of the player
     * @param strength the start strength of the player
     * @param gold the start gold of the player
     * @author Joni Baarda
     */
    protected Player(String name, int health, int maxHealth, float strength, int gold) {
        super(name, maxHealth, strength);
        this.health = health;
        this.gold = gold;
    }


    /**
     * gets the amount of kills for a specific type of npc
     * @param type the type of npc
     * @return the amount of kills
     * @author Joni Baarda
     */
    public int getKillsForType(String type){
        if (killCounter.get(type)==null){
            return 0;
        }
        return killCounter.get(type);
    }

    /**
     * converts the items in the inventory to strings
     * @return the inventory as a list of strings
     * @author Joni Baarda
     */
    public List<String> getInventory(){
        List<String> inventory = new ArrayList<>();
        for (Item item:inventoryItems) {
            if(!inventory.contains(item.getItemAdjective())){
                inventory.add(item.getItemAdjective());
            }
        }
        return inventory;
    }

    /**
     * gets the list of items in the inventory
     * @return the list of items in the inventory
     * @author Joni Baarda
     */
    public List<Item> getInventoryItems(){
        return inventoryItems;
    }

    /**
     * use item calls the right method to execute the item effect and deletes the item from inventory. Then returns what has happened.
     * @param itemName the name of the item that is used
     * @return the explanation of what happened
     * @author Joni Baarda
     */
    public String useItem(String itemName){
        updateEffects();
        Item removeItem = null;
        for (Item item: inventoryItems) {
            if(itemName.equals(item.getItemAdjective())){
                item.use(this);
                removeItem = item;
                break;
            }
        }
        if(removeItem!=null){
            this.inventoryItems.remove(removeItem);
        }
        return "";
    }

    /**
     * counts how many times each item is in the inventory and returns a hashmap of this count.
     * @return a hashmap of the different items and how many times they exist in the inventory
     * @author Joni Baarda
     */
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

    /**
     * Returns the player information about health, energy (mana/stamina), strength, and gold in the form of key-value pairs
     * @return HashMap<String, String> containing the player information key-value pairs
     * @author sfrkovic
     */
    public HashMap<String, String> getPlayerInformation() {
        HashMap<String, String> playerInformation = new HashMap<>();
        playerInformation.put("Health", String.valueOf(getHealth()));
        String energyType = "Energy";
        if (this instanceof Warrior) {
            energyType = "Stamina";
        } else if (this instanceof Mage) {
            energyType = "Mana";
        }
        playerInformation.put(energyType, String.valueOf(getEnergy()));
        playerInformation.put("Strength", String.valueOf(getStrength()));
        playerInformation.put("Gold", String.valueOf(getGold()));
        return playerInformation;
    }

    /**
     * returns the amount of energy a player has
     * @return the amount of energy a player has
     * @author Joni Baarda
     */
    public abstract int getEnergy();

    /**
     * sets the energy level of the player
     * @param energy the new amount of energy of the player
     * @author Joni Baarda
     */
    public abstract void setEnergy(int energy);

    /**
     * gets back a list of the fight actions available to the player
     * @return a list of actions
     * @author Joni Baarda
     */
    public abstract List<Action> getFightActions();

    /**
     * checks if the player is stunned, updates the effects and then calls the fight method.
     * @param action the action
     * @param target the current target of the player
     * @param allEnemies all available enemies in a scene
     * @param scene the current scene
     * @return a string of what has happened
     * @author Joni Baarda
     */
    public String attack(Action action, NPC target, List<NPC> allEnemies, Scene scene){
        if(isStunned()){
            return this.getName() + " are stunned. ";
        }
        updateEffects();
        return fight(action, target, allEnemies, scene);
    }

    /**
     * Calls one of the fight actions the player can take and returns what has happened.
     * @param action the action that is chosen by the player
     * @param target the current target of the player
     * @param allEnemies all available enemies in a scene
     * @param scene the current scene
     * @return a string of what has happened
     * @author Joni Baarda
     */
    protected abstract String fight(Action action, NPC target, List<NPC> allEnemies, Scene scene);

    /**
     * adds the kill to the type of npc in the hashmap.
     * @param type the type of npc the player has killed
     * @author Joni Baarda
     */
    public void addKill(String type){
        if(killCounter.containsKey(type)){
            killCounter.put(type, killCounter.get(type) + 1);
        } else{
            killCounter.put(type, 1);
        }
    }

    /**
     * removes the items to be removed from the inventory and returns the string of items that were removed
     * @param itemsToRemove the list of items that should be removed from the inventory
     * @return a string of which items were removed from the inventory
     * @author Joni Baarda
     */
    public String removeSpecifiedItems(List<Item> itemsToRemove){
        if(itemsToRemove.size()==0){
            return "";
        }
        StringBuilder StringItemsToRemove = new StringBuilder();
        for (Item item:itemsToRemove) {
            for (int i = inventoryItems.size()-1; i >= 0; i--) {
                if(item.getItemAdjective().equals(inventoryItems.get(i).getItemAdjective())){
                    inventoryItems.remove(i);
                    StringItemsToRemove.append(item.getItemAdjective()).append(", ");
                    break;
                }
            }
        }
        String temp = StringItemsToRemove.toString();
        return "You lost " + (temp.substring(0, temp.length()-2)) + ".";
    }

    /**
     * consumes the appropriate potion
     * @author Joni Baarda
     */
    public abstract void useAppropriatePotion();

    /**
     * adds the health of a potion to the player's health
     * @param amount The amount of health to be added
     * @author Joni Baarda
     */
    public void useHealthPotion(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    /**
     * Adds an effect to the player.
     * @param effect The effect that is supposed to be added to the entity.
     * @return A description of what effect was added.
     * @author Joni Baarda
     */
    @Override
    public String addEffect(Effect effect) {
        super.addEffect(effect);
        return getName() + " are " + effect.getEffectAdjective()+ ".";
    }

    /**
     * Determines if the player is confused and therefore attacks itself and sets the confusion chance to 0.
     * @param damage The amount of damage the entity will deal to itself or an ally, in case it is confused.
     * @param allies A List of allies, which might be targeted, if the entity is confused.
     * @return the description how much damage the player has done to themselves
     * @author Joni Baarda
     */
    @Override
    protected String isConfused(int damage, List<NPC> allies){
        String description = "";
        if(Math.random()<confusionChance){
            this.takeDamage(this, damage);
            description = "You have attacked yourself for " + damage + " damage.";
        }
        confusionChance = 0;
        return description;
    }

    /**
     * adds the gold to the player's gold and adds the items to the inventory, then returns a string of what has happened.
     * @param gold the amount of gold that was gained
     * @param items the items that were gained
     * @return the string of what happened
     * @author Joni Baarda
     */
    public String addLoot(int gold, List<Item> items){
        int healthPotionCount = 0; int manaPotionCount = 0; int staminaPotionCount = 0; int removeEffectPotionCount = 0;
        this.gold += gold;
        for (Item item:items) {
            switch (item.getItemAdjective()) {
                case "Health Potion" -> healthPotionCount += 1;
                case "Mana Potion" -> manaPotionCount += 1;
                case "Stamina Potion" -> staminaPotionCount += 1;
                case "Clear Effects Potion" -> removeEffectPotionCount += 1;
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
            return "You gained " + gold + " gold" + stringAddedItems;
        }else{
            return "You lost " + gold + " gold and gained " + stringAddedItems.substring(3);
        }
    }
}
