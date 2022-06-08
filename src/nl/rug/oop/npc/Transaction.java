package nl.rug.oop.npc;

import nl.rug.oop.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transaction extends Dialogue{


    private int goldTransfer;
    private List<Item> playerGains;
    private List<Item> playerLosses;
    private Dialogue nextDialogue;
    private Dialogue previousDialogue;

    public Transaction(Dialogue nextDialogue, int whichSceneNext, int goldTransfer, List<Item> playerGains, List<Item> playerLosses) {
        super("", new HashMap<>(), whichSceneNext);
        this.playerGains = playerGains;
        this.playerLosses = playerLosses;
        this.goldTransfer = goldTransfer;
        this.nextDialogue = nextDialogue;
    }

    public void setPreviousDialogue(Dialogue previousDialogue) {
        this.previousDialogue = previousDialogue;
    }

    public void wasTransactionSuccessful(boolean successful){
        this.removeAllAnswers();
        if (successful || previousDialogue == null){
            this.addAnswer("Continue", nextDialogue);
        }else{
            this.addAnswer("Continue", previousDialogue);
        }
    }

    public int getGoldTransfer() {
        return goldTransfer;
    }

    public List<Item> getPlayerGains() {
        return new ArrayList<>(playerGains);
    }

    public List<Item> getPlayerLosses() {
        return new ArrayList<>(playerLosses);
    }
}
