package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.items.ItemFactory;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * A Trader is a NPC that can talk with the player and sell items, buy items, and/or unlock access to other scenes based on an transaction.
 * @author Jonas Scholz
 */
public class Trader extends TalkingNPC{

    /**
     * Calls the super constructor to initialize the attributes.
     * @param name The name of the NPC.
     * @param type The type of the NPC, for example: Spider.
     * @param maxHealth The maximum health of the NPC.
     * @param strength The strength of the NPC.
     * @param dialogue The dialogue that the NPC can have with the player.
     * @param minGold The minimal amount of gold this NPC will have (inclusive).
     * @param maxGold The maximal amount of gold this NPC will have (exclusive).
     * @param lootItems The list of items that the npc will drop on defeat.
     */
    public Trader(String name, String type, int maxHealth, int strength, Dialogue dialogue, int minGold, int maxGold, ArrayList<Item> lootItems) {
        super(name, type, maxHealth, strength, dialogue, minGold, maxGold, lootItems);
    }

    /**
     * Creates a Trader with already defined health, strength, gold and loot.
     * @param name The name of the NPC.
     * @param dialogue The dialogue that the NPC can have with the player.
     * @param factory The item factory, which is used to create the random loot for the NPC.
     */
    public Trader(String name, Dialogue dialogue, ItemFactory factory){
        this(name, "Trader", 10, 5, dialogue, 5, 15, factory.createRandomItems(1,5));
    }

    /**
     * Evaluates which dialogue comes because of the action and if the new dialogue is a transaction performs the transaction.
     * @param player The player that plays that game.
     * @param currentScene The scene that is currently active, in which this NPC resides.
     * @param action The action that the user took.
     * @return The description of what the trader did.
     */
    @Override
    public String takeNonFightActions(Player player, Scene currentScene, Action action) {
        String description = super.takeNonFightActions(player, currentScene, action);
        if(currentDialogue instanceof Transaction){
            return performTransaction(player);
        }
        return description;
    }


    /**
     * Performs the transaction if the player has the required funds and items.
     * @param player The player that plays the game.
     * @return The description of what happened.
     */
    private String performTransaction(Player player){
        Transaction transaction = (Transaction) currentDialogue;
        if(transaction.getGoldTransfer() <= player.getGold() && hasPlayerRequiredItems(transaction, player)){
            transaction.wasTransactionSuccessful(true);
            player.removeSpecifiedItems(transaction.getPlayerLosses());
            return player.addLoot(-1*transaction.getGoldTransfer(), transaction.getPlayerGains());
        }
        transaction.wasTransactionSuccessful(false);
        return "You have insufficient funds.";
    }

    /**
     * Checks if the player has the items that the player will lose due to the transaction.
     * @param transaction The transaction that is happening.
     * @param player The player that plays the game.
     * @return If the player has the required items.
     */
    private boolean hasPlayerRequiredItems(Transaction transaction, Player player){
        if(transaction.getPlayerLosses().size()>0) {
            List<Item> paymentItems = new ArrayList<>(transaction.getPlayerLosses());
            for (Item item:player.getInventoryItems()) {
                paymentItems.remove(item);
            }
            return paymentItems.size() == 0;
        }
        return true;
    }


}
