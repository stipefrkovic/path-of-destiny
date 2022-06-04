package nl.rug.oop.npc;

import nl.rug.oop.player.Player;
import nl.rug.oop.scene.Action;
import nl.rug.oop.scene.Scene;

public class Trader extends TalkingNPC{

    public Trader(String name, String type, int maxHealth, int strength, Dialogue dialogue) {
        super(name, type, maxHealth, strength, dialogue);
    }

    @Override
    public String takeActions(Player player, Scene currentScene, Action action, boolean isFightAction) {
        String description = super.takeActions(player, currentScene, action, isFightAction);
        if(currentDialogue instanceof Transaction){
            performTransaction(player);
        }
        return description;
    }

    private String performTransaction(Player player){
        Transaction transaction = (Transaction) currentDialogue;
        if(transaction.getGoldTransfer() <= player.getGold() && hasPlayerRequiredItems(transaction, player)){
            //TODO: Finish this function
        }
        return null;
    }

    private boolean hasPlayerRequiredItems(Transaction transaction, Player player){
        //TODO: Finish this function
        return false;
    }


}
