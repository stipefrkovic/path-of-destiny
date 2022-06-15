package nl.rug.oop.dialogue;

import nl.rug.oop.items.Item;
import nl.rug.oop.npc.SceneChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A transaction that keeps track of what is traded for what.
 * @author Jonas Scholz
 */
public class Transaction extends Dialogue{


    private int goldTransfer;
    private List<Item> playerGains;
    private List<Item> playerLosses;
    private Dialogue nextDialogue;
    private Dialogue previousDialogue;

    /**
     * Creates a new Transaction and initializes all attributes except previous dialogue which can be set using the setter.
     * @param nextDialogue The dialogue that should be shown given a successful transaction.
     * @param goldTransfer The amount of gold that is supposed to be transferred, with a positive value being the amount taken from the player and a negative value the amount of gold gained by the player.
     * @param playerGains The items that the player gains due to the transaction.
     * @param playerLosses The items that the player loses due to the transaction.
     */
    public Transaction(Dialogue nextDialogue, int goldTransfer, List<Item> playerGains, List<Item> playerLosses) {
        super("", new HashMap<>(), SceneChange.CURRENT_SCENE);
        this.playerGains = playerGains;
        this.playerLosses = playerLosses;
        this.goldTransfer = goldTransfer;
        this.nextDialogue = nextDialogue;
    }

    /**
     * Sets the optional attribute previousDialogue, which is the next dialogue if the transaction failed.
     * @param previousDialogue The new dialogue that should come next if the transaction failed.
     */
    public void setPreviousDialogue(Dialogue previousDialogue) {
        this.previousDialogue = previousDialogue;
    }

    /**
     * Updates the possible answers based on whether the transaction was successful.
     * @param successful If the transaction is successful
     */
    public void wasTransactionSuccessful(boolean successful){
        this.removeAllAnswers();
        if (successful){
            this.text = "Here you go.";
            this.addAnswer("Continue", nextDialogue);
        }else{
            this.text = "You do not have the required funds or items.";
            if(previousDialogue == null){
                this.addAnswer("Continue", nextDialogue);
            }else{
                this.addAnswer("Continue", previousDialogue);
            }
        }
    }

    /**
     * Returns the amount of gold that gets transferred with positive values indicating that the player pays and negative ones that the player gains the gold.
     * @return The amount of gold that gets transferred with positive values indicating that the player pays and negative ones that the player gains the gold.
     */
    public int getGoldTransfer() {
        return goldTransfer;
    }

    /**
     * Returns the list of items the player gains due to this transaction.
     * @return The list of items the player gains due to this transaction.
     */
    public List<Item> getPlayerGains() {
        return new ArrayList<>(playerGains);
    }

    /**
     * Returns the list of items the player loses due to this transaction.
     * @return The list of items the player loses due to this transaction.
     */
    public List<Item> getPlayerLosses() {
        return new ArrayList<>(playerLosses);
    }
}
