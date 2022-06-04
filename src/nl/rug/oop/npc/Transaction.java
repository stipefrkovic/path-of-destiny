package nl.rug.oop.npc;

import nl.rug.oop.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transaction extends Dialogue{


    private int goldTransfer;
    private List<Item> playerGains;
    private List<Item> playerLosses;

    public Transaction(String text, Dialogue nextDialogue, int whichSceneNext, int goldTransfer, List<Item> playerGains, List<Item> playerLosses) {
        super(text, new HashMap<>(), whichSceneNext);
        this.playerGains = playerGains;
        this.playerLosses = playerLosses;
        this.goldTransfer = goldTransfer;
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
