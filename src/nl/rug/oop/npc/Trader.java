package nl.rug.oop.npc;

import nl.rug.oop.items.Item;
import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Trader extends TalkingNPC{

    public Trader(String name, String type, int maxHealth, int strength, Dialogue dialogue) {
        super(name, type, maxHealth, strength, dialogue);
    }

    public Trader(String name, Dialogue dialogue){
        this(name, "Trader", 10, 5, dialogue);
    }

    @Override
    public String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction) {
        String description = super.takeActions(player, currentScene, action, isFightAction);
        if(currentDialogue instanceof Transaction){
            return performTransaction(player);
        }
        return description;
    }

    private String performTransaction(Player player){
        Transaction transaction = (Transaction) currentDialogue;
        if(transaction.getGoldTransfer() <= player.getGold() && hasPlayerRequiredItems(transaction, player)){
            player.removeSpecifiedItems(transaction.getPlayerLosses());
            return player.addLoot(-1*transaction.getGoldTransfer(), transaction.getPlayerGains());
        }
        return "You have insufficient funds.";
    }

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
